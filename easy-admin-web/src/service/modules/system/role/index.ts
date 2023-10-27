import service from "@/service"

const baseUrl = "/role"

interface IRoleResponse {
  list: IRole[],
  total: number
}


// 创建角色
export function createRole(data: object) {
  return service.post<ResponseData<IRoleResponse>>({
    url: `${baseUrl}/create`, data
  })
}


// 获取角色列表
export function getRoles(params: object) {
  return service.get<ResponseData<IRoleResponse>>({
    url: `${baseUrl}/list`, params
  })
}

// 更改用户信息
export function updateRole(data: object) {
  return service.post<ResponseData<null>>({
    url: `${baseUrl}/update`, data
  })
}

// 删除用户根据id
export function deleteRoleById(id: number) {
  return service.delete<ResponseData<null>>({
    url: `${baseUrl}/delete/${id}`
  })
}

