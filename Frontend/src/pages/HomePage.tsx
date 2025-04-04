import { buttonVariants } from "@/components/ui/button";
import { Link } from "react-router";

const linkClasses = buttonVariants({
  size: "lg",
  className: "w-3xs",
});
function HomePage() {
  return (
    <>
      <div className="flex h-[100vh] w-full flex-col items-center justify-center gap-2">
        <Link to={"/signup/charity"} className={linkClasses}>
          Signup as Charity
        </Link>
        <Link to={"/signup/restaurant"} className={linkClasses}>
          Signup as Restaurant
        </Link>
        <Link to={"/login"} className={linkClasses}>
          Login
        </Link>
      </div>
    </>
  );
}

export default HomePage;
