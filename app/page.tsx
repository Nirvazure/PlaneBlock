import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Card } from "@/components/ui/card"
import { HomeRules } from "@/components/home/HomeRules"
import { HomeFooter } from "@/components/home/HomeFooter"

export default function HomePage() {
  return (
    <div className="flex-1 flex flex-col min-h-0 bg-[#1e1e1e]">
      <div className="flex-1 min-h-0 flex items-center justify-center px-4 py-4">
        <Card className="max-w-6xl w-full p-6 md:p-8 flex flex-col md:flex-row items-center justify-center gap-8 md:gap-10 rounded-none border-0 bg-[#1e1e1e]">
          <div className="flex-1 min-w-0 w-full md:max-w-xl">
            <HomeRules align="left" />
          </div>
          <div className="flex-1 flex flex-col items-center md:items-start shrink-0 w-full md:max-w-sm">
            <Button asChild size="lg" className="w-full h-11 text-sm">
              <Link href="/battle/local">开始本地对战</Link>
            </Button>
          </div>
        </Card>
      </div>
      <HomeFooter />
    </div>
  )
}
