# PlaneBlock

PlaneBlock 是经典纸笔游戏「飞机大战」的本地双人网页版本。两名玩家在同一设备上轮流布置飞机并攻击对方棋盘。

## 功能

- 本地双人对战，无需账号、云端服务或环境变量
- 10×10 棋盘，每位玩家布置 3 架飞机
- 支持飞机方向选择、攻击判定、胜负结算和重新开始

对局状态保存在当前页面内存中。刷新或关闭页面会重新开始，不会上传或保存对局记录。

## 技术栈

- Next.js 14 (App Router)
- React 18、TypeScript
- Tailwind CSS 4、Radix UI

## 本地启动

需要 Node.js 和 pnpm。

```bash
pnpm install
pnpm dev
```

打开终端显示的本地地址即可开始游戏。

```bash
pnpm lint
pnpm build
```

## 路由

| 路径 | 说明 |
|------|------|
| `/` | 游戏规则和开始入口 |
| `/battle/local` | 本地双人对战 |
