import { FormRules } from "element-plus"

export const rules: FormRules = {
  username: [{ required: true, message: "请输入用户名", trigger: 'blur' }],
  nickname: [{ required: true, message: "请输入昵称", trigger: 'blur' }],
  email: [{ required: true, message: "请输入邮箱", trigger: 'blur' }],
  phone: [{ required: true, message: "请输入手机号", trigger: 'blur' }],
  address: [{ required: true, message: "请输入地址", trigger: 'blur' }]
}
