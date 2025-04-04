import { buttonVariants } from "@/components/ui/button";
import { Link } from "react-router";

function HomePage() {
  return (
    <>
      <div className="flex h-[100vh] w-full items-center justify-center">
        <Link
          to={"/login"}
          className={buttonVariants({
            size: "lg",
          })}
        >
          Login
        </Link>
      </div>
    </>
  );
}

export default HomePage;
