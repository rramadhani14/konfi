import type { LoginDto } from "@/model/LoginDto";
import { LoginResponseDto } from "@/model/LoginResponseDto";
import { User } from "@/model/User";
import apiService from "@/services/apiService";
import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", {
    state: () => ({
        user: null as null | User
    }),
    actions: {
        async login(loginDto: LoginDto) {
            const response = await apiService.post<LoginResponseDto>("/api/public/v1/auth/login", loginDto, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            });
            console.log(response);
            if(!(response.status < 399)) {
                throw new Error("Failed to login");
            }
            const token = response.data.accessToken;
            console.log(token);
            window.localStorage.setItem("access_token", token);
            const stringifiedUser = atob(token.split(".")[1]);
            const user = JSON.parse(stringifiedUser);
            this.user = user;
            return user;
        }
    }
})