/// <reference types="vitest" />

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import jsx from '@vitejs/plugin-vue-jsx'
import * as path from "path";

export default defineConfig({
  base: '',
  plugins: [vue(), jsx()],
  resolve:{
    alias:{
      '@':path.resolve(__dirname, "./src")
    }
  },
  test: {
    globals: true,
    environment: 'happy-dom',
    transformMode: {
      web: [/.[tj]sx$/],
    },
  },
})
