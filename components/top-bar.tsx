import Link from "next/link"

export function TopBar() {
  return (
    <header className="flex items-center h-14 px-4 bg-card border-b-[3px] border-[var(--nes-border-dark)] border-t-0 border-x-0">
      <Link href="/" className="flex items-center gap-2 text-sm font-bold text-foreground hover:opacity-80 transition-opacity">
        <img src="/planeBlock.png" alt="PlaneBlock" className="h-8 w-auto object-contain" />
        PlaneBlock
      </Link>
    </header>
  )
}
