"use client"

import { useEffect, useRef, useState } from "react"
import { ArrowDown, ArrowLeft, ArrowRight, ArrowUp } from "lucide-react"
import { GameBoard } from "./game-board"
import { Button } from "./ui/button"
import { toast } from "sonner"
import type { GameState, Airplane, Direction, CellState } from "@/lib/game-types"

const directions: { value: Direction; label: string; Icon: typeof ArrowUp }[] = [
  { value: "up", label: "向上", Icon: ArrowUp },
  { value: "right", label: "向右", Icon: ArrowRight },
  { value: "down", label: "向下", Icon: ArrowDown },
  { value: "left", label: "向左", Icon: ArrowLeft },
]

interface GameSetupProps {
  gameState: GameState
  setGameState: (state: GameState | ((prev: GameState) => GameState)) => void
  disabled?: boolean
}

export function GameSetup({ gameState, setGameState, disabled = false }: GameSetupProps) {
  const [selectedDirection, setSelectedDirection] = useState<Direction>("up")
  const [previewBoard, setPreviewBoard] = useState<CellState[][]>(
    Array(10)
      .fill(null)
      .map(() => Array(10).fill("empty")),
  )
  const [hoveredCell, setHoveredCell] = useState<{ row: number; col: number } | null>(null)

  const currentPlayerAirplanes = gameState.playerAirplanes[gameState.currentPlayer]
  const canPlaceMore = currentPlayerAirplanes.length < 3

  const getAirplaneShape = (headRow: number, headCol: number, direction: Direction) => {
    const positions: { row: number; col: number; type: "head" | "body" }[] = []

    switch (direction) {
      case "up":
        positions.push({ row: headRow, col: headCol, type: "head" })
        for (let i = -2; i <= 2; i++) {
          positions.push({ row: headRow + 1, col: headCol + i, type: "body" })
        }
        positions.push({ row: headRow + 2, col: headCol, type: "body" })
        positions.push({ row: headRow + 3, col: headCol - 1, type: "body" })
        positions.push({ row: headRow + 3, col: headCol, type: "body" })
        positions.push({ row: headRow + 3, col: headCol + 1, type: "body" })
        break
      case "down":
        positions.push({ row: headRow, col: headCol, type: "head" })
        for (let i = -2; i <= 2; i++) {
          positions.push({ row: headRow - 1, col: headCol + i, type: "body" })
        }
        positions.push({ row: headRow - 2, col: headCol, type: "body" })
        positions.push({ row: headRow - 3, col: headCol - 1, type: "body" })
        positions.push({ row: headRow - 3, col: headCol, type: "body" })
        positions.push({ row: headRow - 3, col: headCol + 1, type: "body" })
        break
      case "left":
        positions.push({ row: headRow, col: headCol, type: "head" })
        for (let i = -2; i <= 2; i++) {
          positions.push({ row: headRow + i, col: headCol + 1, type: "body" })
        }
        positions.push({ row: headRow, col: headCol + 2, type: "body" })
        positions.push({ row: headRow - 1, col: headCol + 3, type: "body" })
        positions.push({ row: headRow, col: headCol + 3, type: "body" })
        positions.push({ row: headRow + 1, col: headCol + 3, type: "body" })
        break
      case "right":
        positions.push({ row: headRow, col: headCol, type: "head" })
        for (let i = -2; i <= 2; i++) {
          positions.push({ row: headRow + i, col: headCol - 1, type: "body" })
        }
        positions.push({ row: headRow, col: headCol - 2, type: "body" })
        positions.push({ row: headRow - 1, col: headCol - 3, type: "body" })
        positions.push({ row: headRow, col: headCol - 3, type: "body" })
        positions.push({ row: headRow + 1, col: headCol - 3, type: "body" })
        break
    }

    return positions.filter((pos) => pos.row >= 0 && pos.row < 10 && pos.col >= 0 && pos.col < 10)
  }

  const canPlaceAirplane = (headRow: number, headCol: number, direction: Direction) => {
    const shape = getAirplaneShape(headRow, headCol, direction)

    if (shape.length < 10) {
      return false
    }

    const currentBoard = gameState.playerBoards[gameState.currentPlayer]
    return shape.every((pos) => currentBoard[pos.row][pos.col] === "empty")
  }

  const getPreviewBoard = (row: number, col: number, direction: Direction) => {
    const nextPreview = Array(10)
      .fill(null)
      .map(() => Array(10).fill("empty" as CellState))

    if (canPlaceAirplane(row, col, direction)) {
      getAirplaneShape(row, col, direction).forEach((pos) => {
        nextPreview[pos.row][pos.col] = pos.type === "head" ? "airplane-head" : "airplane-body"
      })
    }

    return nextPreview
  }

  const changeDirection = (direction: Direction) => {
    setSelectedDirection(direction)
    if (hoveredCell && canPlaceMore && !disabled) {
      setPreviewBoard(getPreviewBoard(hoveredCell.row, hoveredCell.col, direction))
    }
  }

  const changeDirectionRef = useRef(changeDirection)
  changeDirectionRef.current = changeDirection

  useEffect(() => {
    if (gameState.phase !== "setup" || !canPlaceMore || disabled) return

    const handleKeyDown = (event: KeyboardEvent) => {
      if (event.repeat || event.altKey || event.ctrlKey || event.metaKey) return
      if (event.target instanceof HTMLElement && event.target.closest("input, textarea, select, [role='combobox'], [contenteditable='true']")) return

      const key = event.key.toLowerCase()
      if (key !== "q" && key !== "e") return

      event.preventDefault()
      const currentIndex = directions.findIndex((direction) => direction.value === selectedDirection)
      const nextIndex = (currentIndex + (key === "e" ? 1 : directions.length - 1)) % directions.length
      changeDirectionRef.current(directions[nextIndex].value)
    }

    window.addEventListener("keydown", handleKeyDown)
    return () => window.removeEventListener("keydown", handleKeyDown)
  }, [canPlaceMore, disabled, gameState.phase, selectedDirection])

  const placeAirplane = (headRow: number, headCol: number) => {
    if (disabled) return
    if (!canPlaceAirplane(headRow, headCol, selectedDirection)) {
      toast.error("无法在此位置放置飞机，请选择其他位置或方向")
      return
    }

    const shape = getAirplaneShape(headRow, headCol, selectedDirection)
    const newBoard = gameState.playerBoards[gameState.currentPlayer].map((row) => [...row])
    const bodyPositions: { row: number; col: number }[] = []

    shape.forEach((pos) => {
      if (pos.type === "head") {
        newBoard[pos.row][pos.col] = "airplane-head"
      } else {
        newBoard[pos.row][pos.col] = "airplane-body"
        bodyPositions.push({ row: pos.row, col: pos.col })
      }
    })

    const newAirplane: Airplane = {
      id: currentPlayerAirplanes.length + 1,
      head: { row: headRow, col: headCol },
      body: bodyPositions,
      direction: selectedDirection,
      isDestroyed: false,
    }

    setGameState((prev) => ({
      ...prev,
      playerBoards: {
        ...prev.playerBoards,
        [gameState.currentPlayer]: newBoard,
      },
      playerAirplanes: {
        ...prev.playerAirplanes,
        [gameState.currentPlayer]: [...currentPlayerAirplanes, newAirplane],
      },
    }))

    setPreviewBoard(
      Array(10)
        .fill(null)
        .map(() => Array(10).fill("empty")),
    )
    setHoveredCell(null)
  }

  const handleCellHover = (row: number, col: number) => {
    if (!canPlaceMore) return
    setHoveredCell({ row, col })
    setPreviewBoard(getPreviewBoard(row, col, selectedDirection))
  }

  const nextPlayer = () => {
    if (disabled) return
    if (gameState.currentPlayer === 1) {
      setGameState((prev) => ({ ...prev, currentPlayer: 2 }))
    } else {
      setGameState((prev) => ({ ...prev, phase: "battle", currentPlayer: 1 }))
    }
    setPreviewBoard(
      Array(10)
        .fill(null)
        .map(() => Array(10).fill("empty")),
    )
    setHoveredCell(null)
  }

  const combinedBoard = gameState.playerBoards[gameState.currentPlayer].map((row, rowIndex) =>
    row.map((cell, colIndex) => {
      if (cell !== "empty") return cell
      return previewBoard[rowIndex][colIndex]
    }),
  )

  return (
    <div className="w-full min-h-[36rem] xl:h-full flex flex-col xl:justify-center">
      <div className="text-center mb-6">
        <h2 className="text-fluid-heading font-bold mb-2">
          <span className={gameState.currentPlayer === 1 ? "text-player-one" : "text-player-two"}>玩家 {gameState.currentPlayer}</span> 布置飞机
        </h2>
        <p className="text-fluid-body text-muted-foreground">已放置 {currentPlayerAirplanes.length}/3 架飞机</p>
        {canPlaceMore && <p className="text-fluid-body text-primary mt-2">选择方向后，点击格子放置飞机机头</p>}
      </div>

      <div className="w-full flex flex-col lg:flex-row items-center justify-center gap-6 lg:gap-12 min-w-0 max-w-6xl mx-auto">
        <div className="w-full min-w-0 lg:w-[34rem] lg:flex-none flex justify-center overflow-hidden">
          <GameBoard
            board={combinedBoard}
            clickableBoard={gameState.playerBoards[gameState.currentPlayer]}
            airplanes={currentPlayerAirplanes}
            isOwn={true}
            player={gameState.currentPlayer}
            onCellClick={canPlaceMore && !disabled ? placeAirplane : () => {}}
            onCellHover={canPlaceMore && !disabled ? handleCellHover : undefined}
            gamePhase="setup"
          />
        </div>

        <div className="w-full max-w-sm lg:w-80 lg:max-w-none shrink-0 space-y-6">
          <div>
            <div className="mb-2 flex items-center justify-between gap-3">
              <span className="text-fluid-body font-medium">飞机方向</span>
              <span className="hidden text-fluid-label text-muted-foreground lg:inline">Q / E 旋转</span>
            </div>
            <div role="group" aria-label="飞机方向" className="grid grid-cols-4 gap-2">
              {directions.map(({ value, label, Icon }) => (
                <Button
                  key={value}
                  type="button"
                  size="icon"
                  variant={selectedDirection === value ? "default" : "outline"}
                  aria-pressed={selectedDirection === value}
                  aria-label={label}
                  title={label}
                  disabled={disabled || !canPlaceMore}
                  onClick={() => changeDirection(value)}
                >
                  <Icon aria-hidden="true" />
                </Button>
              ))}
            </div>
          </div>

          <div className="space-y-4">
            <h3 className="text-fluid-body font-bold">游戏规则</h3>
            <ul className="text-fluid-body text-muted-foreground space-y-2">
              <li>• 每个玩家需要放置3架飞机</li>
              <li>• 飞机形状：机头1格 + 机翅5格 + 机尾3格</li>
              <li>• 飞机可以朝四个方向放置</li>
              <li>• 击中机身得「中」，击中机头得「死」</li>
              <li>• 击毁对方3架飞机获胜</li>
            </ul>
          </div>

          {currentPlayerAirplanes.length === 3 && (
            <Button onClick={nextPlayer} className="w-full" disabled={disabled}>
              {gameState.currentPlayer === 1 ? "玩家2布置飞机" : "开始游戏"}
            </Button>
          )}
        </div>
      </div>
    </div>
  )
}
