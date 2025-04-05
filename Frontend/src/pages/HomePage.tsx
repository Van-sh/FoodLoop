import { Link, useNavigate } from "react-router";

import { logoutUser } from "@/api/user";

import { Button, buttonVariants } from "@/components/ui/button";

const linkClasses = buttonVariants({
  size: "lg",
  className: "w-3xs",
});
function HomePage() {
  const navigate = useNavigate();

  return (
    <>
      <div className="flex h-[100vh] w-full flex-col items-center justify-center gap-2">
        {localStorage.getItem("jwt") ? (
          <Button
            onClick={() => {
              logoutUser().then(() => {
                navigate("/");
              });
            }}
          >
            Logout
          </Button>
        ) : (
          <>
            <Link to={"/signup/charity"} className={linkClasses}>
              Signup as Charity
            </Link>
            <Link to={"/signup/restaurant"} className={linkClasses}>
              Signup as Restaurant
            </Link>
            <Link to={"/login"} className={linkClasses}>
              Login
            </Link>
          </>
        )}
      </div>
    </>
  );
}

export default HomePage;
