<script setup lang="ts">
import { ref, type HTMLAttributes } from "vue"
import { cn } from "@/lib/utils"
import { Button } from '@/components/ui/button'
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { useAuthStore } from "@/stores/authStore"

const props = defineProps<{
  class?: HTMLAttributes["class"]
}>()

const loginForm = ref({
  "organization": "",
  "username": "",
  "password": ""
})

async function handleLogin() {
  const user = await useAuthStore().login(loginForm.value);
  console.log(user);
}
</script>

<template>
  <div :class="cn('flex flex-col gap-6', props.class)">
    <Card>
      <CardHeader class="text-center">
        <CardTitle class="text-xl">
          Welcome back
        </CardTitle>
        <CardDescription>
          Login to Konfi
        </CardDescription>
      </CardHeader>
      <CardContent>
        <form @submit.prevent="handleLogin">
          <div class="grid gap-6">
            <div class="grid gap-6">

              <div class="grid gap-3">
                <Label for="organization">Organization</Label>
                <Input id="organization" type="text" placeholder="test" required v-model="loginForm.organization" />
              </div>

              <div class="grid gap-3">
                <Label for="username">Username</Label>
                <Input id="username" type="text" placeholder="m@example.com" required v-model="loginForm.username" />
              </div>

              <div class="grid gap-3">
                <div class="flex items-center">
                  <Label for="password">Password</Label>
                  <a href="#" class="ml-auto text-sm underline-offset-4 hover:underline">
                    Forgot your password?
                  </a>
                </div>
                <Input id="password" type="password" v-model="loginForm.password" require />
              </div>
              <Button type="submit" class="w-full">
                Login
              </Button>
            </div>
          </div>
        </form>
      </CardContent>
    </Card>

  </div>
</template>
