import z from "zod";

export const LoginDto = z.object({
    organization: z.string().min(1),
    username: z.string().min(1),
    password: z.string().min(1)
})

export type LoginDto = z.infer<typeof LoginDto>