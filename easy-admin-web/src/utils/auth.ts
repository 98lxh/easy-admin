export const TokenKey = "Easy-Admin-Token";

export function setToken(token: string) {
  localStorage.setItem(TokenKey, token)
}

export function getToken() {
  return localStorage.getItem(TokenKey) || ""
}

export function removeToken() {
  return localStorage.removeItem(TokenKey)
}
