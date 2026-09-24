import Link from "next/link"
import { Button } from "@/components/ui/button"
import { HomeRules } from "@/components/home/HomeRules"
import { HomeFooter } from "@/components/home/HomeFooter"

export default function HomePage() {
  return (
    <div className="min-h-full flex flex-col bg-[#1e1e1e]">
      <div className="flex-1 flex items-center justify-center px-5 py-8 sm:px-8">
        <div className="w-full max-w-2xl">
          <HomeRules />
          <div className="mt-7 flex justify-center">
            <Button asChild size="lg" className="w-full max-w-xs h-12 text-sm">
              <Link href="/battle/local">开始本地对战</Link>
            </Button>
          </div>
        </div>
      </div>
      <HomeFooter />
    </div>
  )
}
