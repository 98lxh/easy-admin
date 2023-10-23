import { FormRules } from "element-plus"

export const rules: FormRules = {
  username: [{ required: true, message: "请输入用户名", trigger: 'blur' }],
  password: [{ required: true, message: "请输入密码", trigger: 'blur' }],
  nickname: [{ required: true, message: "请输入昵称", trigger: 'blur' }]
}
