import service from "@/service"

const baseUrl = "/user"

interface IUserResponse {
  list: IUser[],
  total: number
}

// 登录
export function login(data: object) {
  return service.post<ResponseData<null>>({
    url: `${baseUrl}/login`,
    data
  })
}

// 注册
export function register(data:object){
  return service.post<ResponseData<null>>({
    url: `${baseUrl}/register`,
    data
  })
}

// 获取用户列表
export function getUsers(params: object) {
  return service.get<ResponseData<IUserResponse>>({
    url: `${baseUrl}/list`, params
  })
}

// 更改用户信息
export function updateUser(data: object) {
  return service.post<ResponseData<null>>({
    url: `${baseUrl}/update`, data
  })
}

// 删除用户根据id
export function deleteUserById(id: number) {
  return service.delete<ResponseData<null>>({
    url: `${baseUrl}/delete/${id}`
  })
}

