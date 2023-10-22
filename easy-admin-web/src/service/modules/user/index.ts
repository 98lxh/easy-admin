import service from "@/service"

const baseUrl = "/user"

export function login(data: object){
  return service.post<ResponseData<any>>({
    url:`${baseUrl}//login`,
    data
  })
}

// 获取用户列表
export function requestUsers(params: object){
  return service.get<ResponseData<any>>({
    url:`${baseUrl}/list`,  params
  })
}
