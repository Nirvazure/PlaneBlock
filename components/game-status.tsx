"use client"

import type { GameState } from "@/lib/game-types"

interface GameStatusProps {
  gameState: GameState
  compact?: boolean
}

export function GameStatus({ gameState, compact = false }: GameStatusProps) {
  const getPlayerAccent = (player: 1 | 2) => (player === 1 ? "text-player-one" : "text-player-two")

  const getPlayerStats = (player: 1 | 2) => {
    const airplanes = gameState.playerAirplanes[player]
    const destroyed = airplanes.filter((a) => a.isDestroyed).length
    const remaining = airplanes.length - destroyed
    return { total: airplanes.length, destroyed, remaining }
  }

  const activePhaseIndex = gameState.phase === "setup" ? 0 : 1
  const phases = ["布置", "战斗"]

  const barContent = (
    <div className="flex flex-1 flex-row items-center justify-between gap-2 sm:gap-4 min-w-0">
      <div className="flex min-w-0 items-center gap-2 sm:gap-4">
        {gameState.phase === "finished" ? (
          <span className="text-fluid-label font-bold">
            游戏结束 <span className="text-muted-foreground">·</span>{" "}
            <span className={gameState.winner ? getPlayerAccent(gameState.winner) : undefined}>玩家 {gameState.winner} 获胜</span>
          </span>
        ) : (
          <ol aria-label="游戏阶段" className="flex shrink-0 items-center gap-2 sm:gap-3">
            {phases.map((phase, index) => {
              const isCurrent = index === activePhaseIndex
              const isComplete = index < activePhaseIndex

              return (
                <li key={phase} className="flex items-center gap-1.5 sm:gap-2" aria-current={isCurrent ? "step" : undefined}>
                  <span
                    aria-hidden="true"
                    className={`size-1.5 shrink-0 ${isCurrent ? "bg-foreground" : isComplete ? "bg-muted-foreground" : "border border-muted-foreground"}`}
                  />
                  <span className={`text-fluid-label ${isCurrent ? "font-bold text-foreground" : "text-muted-foreground"}`}>
                    {phase}
                  </span>
                  {index === 0 && (
                    <span aria-hidden="true" className="ml-0.5 h-px w-3 bg-muted-foreground/50 sm:ml-1 sm:w-5" />
                  )}
                </li>
              )
            })}
          </ol>
        )}
        {gameState.phase === "battle" && (
          <span className="truncate text-fluid-label font-bold">
            回合: <span className={getPlayerAccent(gameState.currentPlayer)}>玩家 {gameState.currentPlayer}</span>
          </span>
        )}
      </div>
      <div className="flex gap-4 sm:gap-6">
        {[1, 2].map((player) => {
          const stats = getPlayerStats(player as 1 | 2)
          return (
            <div key={player} className="text-center min-w-0">
              <div className={`text-fluid-label font-bold ${getPlayerAccent(player as 1 | 2)}`}>P{player}</div>
              <div className="text-fluid-body font-bold">
                {stats.remaining}/{stats.total}
              </div>
              {stats.destroyed > 0 && (
                <div className="text-fluid-label text-muted-foreground">击毁 {stats.destroyed}</div>
              )}
            </div>
          )
        })}
      </div>
    </div>
  )

  if (compact) {
    return barContent
  }

  return (
    <div className="flex items-center gap-4 p-3 border-[3px] border-[var(--nes-border-dark)] border-t-[var(--nes-border-light)] border-l-[var(--nes-border-light)] bg-card">
      {barContent}
    </div>
  )
}
