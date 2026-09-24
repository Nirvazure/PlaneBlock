"use client"

import { useState } from "react"
import { toast } from "sonner"
import { GameBoard } from "@/components/game-board"
import { GameSetup } from "@/components/game-setup"
import { GameStatus } from "@/components/game-status"
import { HomeFooter } from "@/components/home/HomeFooter"
import { Button } from "@/components/ui/button"
import { initialBoard, type GameState } from "@/lib/game-types"

type BattleView = "mine" | "attack"

export default function LocalBattlePage() {
  const [battleView, setBattleView] = useState<BattleView>("mine")
  const [gameState, setGameState] = useState<GameState>({
    phase: "setup",
    currentPlayer: 1,
    playerBoards: {
      1: initialBoard(),
      2: initialBoard(),
    },
    playerAirplanes: {
      1: [],
      2: [],
    },
    attackBoards: {
      1: initialBoard(),
      2: initialBoard(),
    },
    winner: null,
  })

  const resetGame = () => {
    setGameState({
      phase: "setup",
      currentPlayer: 1,
      playerBoards: {
        1: initialBoard(),
        2: initialBoard(),
      },
      playerAirplanes: {
        1: [],
        2: [],
      },
      attackBoards: {
        1: initialBoard(),
        2: initialBoard(),
      },
      winner: null,
    })
  }

  const opponent = gameState.currentPlayer === 1 ? 2 : 1
  const receivedAttackBoard = gameState.attackBoards[opponent]
  const ownBattleBoard = gameState.playerBoards[gameState.currentPlayer].map((row, rowIndex) =>
    row.map((cell, colIndex) => {
      const attackState = receivedAttackBoard[rowIndex][colIndex]
      return attackState === "empty" ? cell : attackState
    }),
  )

  return (
    <div className="flex min-h-full flex-col bg-background xl:h-full xl:min-h-0">
      <div className="flex-1 p-3 sm:p-4 lg:p-5">
        <section className="mx-auto flex min-h-[40rem] w-full max-w-7xl flex-col border-[3px] border-[var(--nes-border-dark)] border-t-[var(--nes-border-light)] border-l-[var(--nes-border-light)] bg-card p-4 sm:p-6 xl:h-full xl:min-h-0">
          <div className="flex items-center justify-between gap-3 border-b-2 border-[var(--nes-border-dark)] pb-3">
            <Button onClick={resetGame} variant="outline" className="shrink-0 rounded-none px-3 py-2 text-fluid-label">
              重新开始
            </Button>
            <div className="min-w-0 flex-1">
              <GameStatus gameState={gameState} compact />
            </div>
          </div>

          <div className="pt-4 xl:min-h-0 xl:flex-1">
            {gameState.phase === "setup" ? (
              <GameSetup gameState={gameState} setGameState={setGameState} />
            ) : (
              <div className="mx-auto grid h-full min-w-0 w-full max-w-6xl grid-cols-1 gap-4 lg:grid-cols-2 lg:gap-6">
                <div className="flex gap-2 lg:hidden">
                  <Button
                    variant={battleView === "mine" ? "default" : "outline"}
                    size="sm"
                    className="flex-1"
                    onClick={() => setBattleView("mine")}
                  >
                    我的战场
                  </Button>
                  <Button
                    variant={battleView === "attack" ? "default" : "outline"}
                    size="sm"
                    className="flex-1"
                    onClick={() => setBattleView("attack")}
                  >
                    攻击对手
                  </Button>
                </div>

                <div className={`min-h-[360px] min-w-0 overflow-hidden border-t-2 border-[var(--nes-border-dark)] py-4 lg:min-h-0 ${battleView !== "mine" ? "hidden lg:block" : ""}`}>
                  <h3 className="mb-2 text-center text-fluid-body font-bold">
                    <span className={gameState.currentPlayer === 1 ? "text-player-one" : "text-player-two"}>玩家 {gameState.currentPlayer}</span> - 我的战场
                  </h3>
                  <p className="mb-4 text-center text-fluid-label text-muted-foreground">对手攻击：× 命中 · 💥 击毁 · ○ 未中</p>
                  <div className="flex w-full min-w-0 justify-center">
                    <GameBoard
                      board={ownBattleBoard}
                      airplanes={gameState.playerAirplanes[gameState.currentPlayer]}
                      isOwn={true}
                      player={gameState.currentPlayer}
                      hitColorPlayer={opponent}
                      onCellClick={() => {}}
                      gamePhase={gameState.phase}
                    />
                  </div>
                </div>

                <div className={`min-h-[360px] min-w-0 overflow-hidden border-t-2 border-[var(--nes-border-dark)] py-4 lg:min-h-0 ${battleView !== "attack" ? "hidden lg:block" : ""}`}>
                  <h3 className="mb-4 text-center text-fluid-body font-bold">攻击对手</h3>
                  <div className="flex w-full min-w-0 justify-center">
                    <GameBoard
                      board={gameState.attackBoards[gameState.currentPlayer]}
                      airplanes={[]}
                      isOwn={false}
                      hitColorPlayer={gameState.currentPlayer}
                      onCellClick={(row, col) => {
                        if (gameState.phase !== "battle") return

                        const opponent = gameState.currentPlayer === 1 ? 2 : 1
                        const opponentBoard = gameState.playerBoards[opponent]
                        const attackBoard = [...gameState.attackBoards[gameState.currentPlayer]]

                        if (attackBoard[row][col] !== "empty") return

                        let result: "hit" | "miss" | "destroy" = "miss"

                        if (opponentBoard[row][col] === "airplane-body" || opponentBoard[row][col] === "airplane-head") {
                          result = "hit"
                          attackBoard[row][col] = opponentBoard[row][col] === "airplane-head" ? "destroyed" : "hit"

                          if (opponentBoard[row][col] === "airplane-head") {
                            result = "destroy"
                            const updatedAirplanes = gameState.playerAirplanes[opponent].map((airplane) => {
                              if (airplane.head.row === row && airplane.head.col === col) {
                                return { ...airplane, isDestroyed: true }
                              }
                              return airplane
                            })

                            const destroyedCount = updatedAirplanes.filter((airplane) => airplane.isDestroyed).length

                            if (destroyedCount === 3) {
                              setGameState((prev) => ({
                                ...prev,
                                playerAirplanes: {
                                  ...prev.playerAirplanes,
                                  [opponent]: updatedAirplanes,
                                },
                                attackBoards: {
                                  ...prev.attackBoards,
                                  [gameState.currentPlayer]: attackBoard,
                                },
                                phase: "finished",
                                winner: gameState.currentPlayer,
                              }))
                            } else {
                              setGameState((prev) => ({
                                ...prev,
                                playerAirplanes: {
                                  ...prev.playerAirplanes,
                                  [opponent]: updatedAirplanes,
                                },
                                attackBoards: {
                                  ...prev.attackBoards,
                                  [gameState.currentPlayer]: attackBoard,
                                },
                                currentPlayer: prev.currentPlayer === 1 ? 2 : 1,
                              }))
                            }
                            toast.success("击毁飞机！")
                            return
                          }
                        } else {
                          attackBoard[row][col] = "miss"
                        }

                        setGameState((prev) => ({
                          ...prev,
                          attackBoards: {
                            ...prev.attackBoards,
                            [gameState.currentPlayer]: attackBoard,
                          },
                          currentPlayer: prev.currentPlayer === 1 ? 2 : 1,
                        }))

                        toast.success(result === "hit" ? "击中！" : "未击中")
                      }}
                      gamePhase={gameState.phase}
                    />
                  </div>
                </div>
              </div>
            )}
          </div>
        </section>
      </div>
      <HomeFooter />
    </div>
  )
}
