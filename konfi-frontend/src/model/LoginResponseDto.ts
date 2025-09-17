import z from "zod";


export const LoginResponseDto = z.object({
    accessToken: z.string()
})

export type LoginResponseDto = z.infer<typeof LoginResponseDto>