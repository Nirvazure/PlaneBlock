import { TopBar } from "./top-bar"

interface LayoutClientProps {
  children: React.ReactNode
}

export function LayoutClient({ children }: LayoutClientProps) {
  return (
    <div className="flex flex-col h-screen overflow-hidden">
      <TopBar />
      <main className="flex-1 min-h-0 flex flex-col overflow-auto">{children}</main>
    </div>
  )
}
