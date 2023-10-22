import { FormRules } from "element-plus"

export const rules:FormRules = {
  username:[{ required:true, message:"请输入用户名", trigger:'blur' }],
  password:[{ required:true, message:"请输入密码", trigger:'blur' }],
  code:[{required:true,message:'请输入图片验证码',trigger:'blur'}]
}
