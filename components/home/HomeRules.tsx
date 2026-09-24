"use client"

export function HomeRules() {
  return (
    <section className="text-center">
      <img
        src="/planeBlock.png"
        alt="PlaneBlock"
        className="max-w-[180px] w-44 mb-4 mx-auto"
      />
      <h1 className="text-2xl font-bold mb-6">PlaneBlock</h1>
      <div className="space-y-3">
        <h2 className="text-base font-bold mb-3">游戏规则</h2>
        <ul className="max-w-xl mx-auto text-left text-sm text-muted-foreground space-y-2 list-disc list-inside leading-relaxed">
          <li>双方在 10×10 棋盘上各放置 3 架飞机（每架 10 格：机头 1 格、机翼 5 格、机身 1 格、机尾 3 格）</li>
          <li>布置阶段：轮流选择方向与位置放置飞机</li>
          <li>战斗阶段：轮流点击对手棋盘格子进行攻击</li>
          <li>击中机头可击毁整架飞机，率先击毁对方 3 架飞机者获胜</li>
        </ul>
      </div>
    </section>
  )
}
