import service from "@/service"


export function login(data:object){
  return service.post<ResponseData<any>>({
    url:"/login",
    data
  })
}

export function requestCaptchaUrl(){

}
