// 用户信息
declare interface IUser {
  id?: number
  token?: string
  username: string
  password: string
  nickname: string
  address: string
  email: string
  phone: string
}

// 角色信息
declare interface IRole {
  name: string
  descrption: string
}
