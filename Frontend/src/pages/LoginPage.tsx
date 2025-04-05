import { useForm } from "@tanstack/react-form";
import { useEffect } from "react";
import { useNavigate, useParams } from "react-router";
import { z } from "zod";

import { loginUser } from "@/api/user";

import FieldInfo from "@/components/FieldInfo";
import { BottomGradient, LabelInputContainer } from "@/components/signup-form-demo";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { cn } from "@/lib/utils";

const loginSchema = z.object({
  email: z.string().email("Must be an email"),
  password: z
    .string()
    .min(8, "Password must be at least 8 characters")
    .max(20, "Password must be at most 20 characters"),
});

function LoginPage() {
  const { role } = useParams();
  const navigate = useNavigate();

  const form = useForm({
    defaultValues: {
      email: "",
      password: "",
    },
    validators: {
      onChange: loginSchema,
    },
    onSubmit: async ({ value }) => {
      console.log(value);
      loginUser(value).then(() => {
        navigate("/");
      });
    },
  });

  useEffect(() => {
    if (role && ["charity", "restaurant"].indexOf(role) == -1) {
      navigate("/");
    }
  });

  return (
    <div className="flex h-[100vh] w-full flex-col items-center justify-center gap-2">
      <div className="shadow-input mx-auto w-full max-w-md rounded-none bg-white p-4 md:rounded-2xl md:p-8 dark:bg-black">
        <h2 className="text-2xl font-bold text-neutral-800 dark:text-neutral-200">
          Welcome to FoodLoop
        </h2>
        <form
          className="my-8"
          onSubmit={(e) => {
            e.preventDefault();
            e.stopPropagation();
            form.handleSubmit();
          }}
        >
          <div className="flex flex-col gap-4">
            <form.Field
              name="email"
              children={(field) => (
                <>
                  <LabelInputContainer>
                    <Label htmlFor={field.name}>Email</Label>
                    <Input
                      id={field.name}
                      name={field.name}
                      value={field.state.value}
                      onBlur={field.handleBlur}
                      onChange={(e) => field.handleChange(e.target.value)}
                      placeholder="amansharma@gmail.com"
                      className={cn({
                        "outline-1 outline-red-500 dark:outline-red-400":
                          field.state.meta.isTouched && field.state.meta.errors.length,
                      })}
                      autoFocus
                    />
                    <FieldInfo field={field} />
                  </LabelInputContainer>
                </>
              )}
            />
            <form.Field
              name="password"
              children={(field) => (
                <>
                  <LabelInputContainer>
                    <Label htmlFor={field.name}>Password</Label>
                    <Input
                      id={field.name}
                      name={field.name}
                      value={field.state.value}
                      onBlur={field.handleBlur}
                      onChange={(e) => field.handleChange(e.target.value)}
                      type="password"
                      placeholder="••••••••"
                      className={cn({
                        "outline-1 outline-red-500 dark:outline-red-400":
                          field.state.meta.isTouched && field.state.meta.errors.length,
                      })}
                    />
                    <FieldInfo field={field} />
                  </LabelInputContainer>
                </>
              )}
            />
          </div>

          <form.Subscribe
            selector={(state) => state.canSubmit}
            children={
              <button
                className={cn(
                  "group/btn relative block h-10 w-full rounded-md bg-gradient-to-br from-black to-neutral-600 font-medium text-white shadow-[0px_1px_0px_0px_#ffffff40_inset,0px_-1px_0px_0px_#ffffff40_inset] dark:bg-zinc-800 dark:from-zinc-900 dark:to-zinc-900 dark:shadow-[0px_1px_0px_0px_#27272a_inset,0px_-1px_0px_0px_#27272a_inset]",
                  "mt-8",
                )}
                type="submit"
              >
                Sign up &rarr;
                <BottomGradient />
              </button>
            }
          />
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
