<template>
<body>
  <div class="wrapper">
      <div class="card" v-if="check">
          <form action="#" class="d-flex flex-column">
              <div class="h3 text-center text-white">Login</div>
              <div class="d-flex align-items-center input-field mt-3 mb-1" > <img src="https://i.ibb.co/944cbhT/user-icon.png" alt="" style="width: 30px; background-color: #;"><input type="text" placeholder="Email" required class="form-control" v-model="loginData.email" @blur="emailValidation"> </div>
              <div v-if="!loginData.emailValidationFlag" align="left" style="color:red;">유효하지 않은 이메일이요</div>
              <div class="d-flex align-items-center input-field my-4"> <img src="https://i.ibb.co/gjVKMfj/lock-icon.png" style="width: 30px;"> <input type="password" placeholder="Password" required class="form-control" id="pwd" v-model="loginData.password" > <img src="https://i.ibb.co/hZ9Kt4j/eye-icon.png" style="width: 30px;" alt=""></div>
              <div class="d-sm-flex align-items-sm-center justify-content-sm-between">
                  <!-- <div class="d-flex align-items-center"> <label class="option"> <span class="text-light-white">Remember Me</span> <input type="checkbox" checked> <span class="checkmark"></span> </label> </div>
                  <div class="mt-sm-0 mt-3"><a href="#">Forgot password?</a></div> -->
              </div>
              <div class="my-3" style="margin: 0 auto;"> <span class="btn btn-primary" style="" @click="login()">Login</span> </div>
              <div class="mb-3" style="margin: 0 auto;"> <span class="text-light-white">Don't have an account?</span> <a @click="goSignup">Sign Up</a> </div>
          </form>
          <div class="position-relative border-bottom my-3 line"> <span class="connect">or connect with</span> </div>
          <div class="text-center py-3 connections"> 
              
              <a target="_blank" class="px-2" @click="kakaologin()"> 
                <!-- <img src="https://www.freepnglogos.com/uploads/google-logo-png/google-logo-png-suite-everything-you-need-know-about-google-newest-0.png" alt=""> -->
              </a> 
              <LoginKakao/>
          </div>
      </div>

      <div class="card" v-else>
          <form action="#" class="d-flex flex-column">
              <div class="h3 text-center text-white">Sign up</div>
              <div class="d-flex align-items-center input-field mt-3 mb-1" > <img src="https://i.ibb.co/944cbhT/user-icon.png" alt="" style="width: 30px; background-color: #;"> <input type="text" placeholder="Email" required class="form-control" v-model="signupData.email" @blur="SignupEmailValidation"> </div>
              <div v-if="!signupData.emailValidationFlag" align="left" style="color:red;" >유효하지 않은 이메일이요</div>
              <div class="d-flex align-items-center input-field my-3 mb-4" > <img src="https://i.ibb.co/944cbhT/user-icon.png" alt="" style="width: 30px; background-color: #;"> <input type="text" placeholder="username" required class="form-control" v-model="signupData.username"> </div>
              <div class="d-flex align-items-center input-field mb-3"> <img src="https://i.ibb.co/gjVKMfj/lock-icon.png" style="width: 30px;"> <input type="password" placeholder="Password" required class="form-control" v-model="signupData.password" @blur="passwordValidation"><img src="https://i.ibb.co/hZ9Kt4j/eye-icon.png" style="width: 30px;" alt=""></div>
              <div class="mb-2" v-if="!passwordValidFlag" align="left">Your password is not allowed</div>
              <div class="d-flex align-items-center input-field mb-3"> <img src="https://i.ibb.co/gjVKMfj/lock-icon.png" style="width: 30px;"> <input type="password" placeholder="PasswordConfirmation" required class="form-control" v-model="signupData.passwordConfirmation" @blur="passwordCheck"><img src="https://i.ibb.co/hZ9Kt4j/eye-icon.png" style="width: 30px;" alt=""></div>
              <div class="mb-2" align="left" v-if="!passwordFlag" >Check your password</div>
              <div class="d-flex align-items-center input-field mb-4"> <input type="text" placeholder="Admin Code" required class="form-control" v-model="signupData.roles"></div>
              <!-- <div class="d-sm-flex align-items-sm-center justify-content-sm-between" style="padding-left:0px">
                <div class="d-flex align-items-center input-field mb-4 w-75" style="margin-right:10px;" > <span class="fas fa-lock p-2"></span> <input type="text" placeholder="phone" required class="form-control" > <button class="btn" onclick="showPassword()"> <span class="fas fa-eye-slash"></span> </button> </div>
                <button class="w-25" style="height:50px;margin-bottom:25px; padding-left:0px;">인증</button>
              
              </div> -->
              <!-- <input type="text" v-model="signupData.roles"> -->
              <div class="my-3" style="margin:0 auto;"> <button class="btn btn-primary" @click="signup">Sign up</button> </div>
              <div class="mb-3" style="margin:0 auto;"> <span class="text-light-white">Are you an admin?</span> <span @click="goSignup">Login</span> </div>
              
          </form>
          <div class="position-relative border-bottom my-3 line" style="margin-left: 7%;"> <span class="connect">Easy Sign up</span> </div>
          <!-- <div class="text-center py-3 connections"> <a href="https://wwww.facebook.com" target="_blank" class="px-2"> <img src="https://www.dpreview.com/files/p/articles/4698742202/facebook.jpeg" alt=""> </a> <a href="https://www.google.com" target="_blank" class="px-2"> <img src="https://www.freepnglogos.com/uploads/google-logo-png/google-logo-png-suite-everything-you-need-know-about-google-newest-0.png" alt=""> </a> <a href="https://www.github.com" target="_blank" class="px-2"> <img src="https://www.freepnglogos.com/uploads/512x512-logo-png/512x512-logo-github-icon-35.png" alt=""> </a> </div> -->
      </div>
  </div>
  
</body>
</template>

<script>
import {ref,computed} from 'vue'
import {useStore} from 'vuex'
import LoginKakao from '../LoginKakao.vue'
import {useRouter} from 'vue-router'
export default {
  emits : ['login-value'],
  name : "LoginModal",
  components : {
      LoginKakao,
  },
  setup(props,{emit}) {
    const router = useRouter()
    const store = useStore()
    const loginData = ref({
        email : '',
        password : '',
        emailValidationFlag : true,
    })
    const signupData = ref({
        name : '',
        email : '',
        username : '',
        password : '',  
        passwordConfirmation : '',
        roles : '',
        emailValidationFlag : true,
        
    }) 
    const message = ''
    const passwordFlag = ref(true)
    const passwordValidFlag = ref(true)



    // function showPassword() {
    //     var password = document.getElementById('pwd');
    //     if (password.type === 'password') {
    //         password.type = "text";
    //     } else {
    //         password.type = "password";
    //     }
    // }

    var check= ref(1)

    const goSignup = () => {
        console.log(check.value)
        if (check.value === 0) {
            check.value += 1
        } else {
            check.value -= 1
        }
    }

    const login = () => {
        console.log('1로그인 메소드 실행')
        console.log(loginData.value)
       
        emit('login-value',loginData.value)
        // store.dispatch("auth/login", loginData)
        // console.log('??')
        
    
        
    }
    const kakaologin = () => {
        emit('kakao-login')
    }

    const signup = () => {
        if (signupData.value.roles === '') {
            signupData.value.roles = ["ROLE_USER"]
        } else if (signupData.value.roles === 'store1') {
            signupData.value['store'] = 'cartier' 
            signupData.value.roles = ["ROLE_STORE_ADMIN"]
        } else if (signupData.value.roles === 'store2') {
            signupData.value['store'] = 'gucci' 
            signupData.value.roles = ["ROLE_STORE_ADMIN"]
        } else if (signupData.value.roles === 'store3') {
            signupData.value['store'] = 'versace' 
            signupData.value.roles = ["ROLE_STORE_ADMIN"]
        } else if (signupData.value.roles === 'store4') {
            signupData.value['store'] = 'louis vuitton' 
            signupData.value.roles = ["ROLE_STORE_ADMIN"]
        } else if (signupData.value.roles === 'store5') {
            signupData.value['store'] = 'prada' 
            signupData.value.roles = ["ROLE_STORE_ADMIN"]
        } else if (signupData.value.roles === 'store6') {
            signupData.value['store'] = 'fendi' 
            signupData.value.roles = ["ROLE_STORE_ADMIN"]
        }
        
        else if (signupData.value.roles === 'brand1') {
            signupData.value.name = 'cartier'
            signupData.value.roles = ["ROLE_BRAND_ADMIN"]
        } else if (signupData.value.roles === 'brand2') {
            signupData.value.name = 'gucci'
            signupData.value.roles = ["ROLE_BRAND_ADMIN"]
        } else if (signupData.value.roles === 'brand3') {
            signupData.value.name = 'versace'
            signupData.value.roles = ["ROLE_BRAND_ADMIN"]
        } else if (signupData.value.roles === 'brand4') {
            signupData.value.name = 'louis vuitton'
            signupData.value.roles = ["ROLE_BRAND_ADMIN"]
        } else if (signupData.value.roles === 'brand5') {
            signupData.value.name = 'prada'
            signupData.value.roles = ["ROLE_BRAND_ADMIN"]
        } else if (signupData.value.roles === 'brand6') {
            signupData.value.name = 'fendi'
            signupData.value.roles = ["ROLE_BRAND_ADMIN"]
        } 
        console.log(signupData.value)
        console.log(signupData.value.name)
        // console.log(typeof(signupData.value.roles))
        store.dispatch("auth/register", signupData.value)
        // console.log('휴휴')
    }

    
    //정규식
//     computed: {
//     emailValidation() {
//         let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
//         return re.test(this.userItems.id);
//     }
// }
    const emailValidation = computed(() => {
        let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (re.test(loginData.value.email)) {
            loginData.value.emailValidationFlag = true
        } else {
            loginData.value.emailValidationFlag = false
        }
    })

    const SignupEmailValidation = computed(() => {
        let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if (re.test(signupData.value.email)) {
            signupData.value.emailValidationFlag = true
        } else {
            signupData.value.emailValidationFlag = false
        }
    })

    const passwordCheck = computed(() => {
        if ( signupData.value.password == signupData.value.passwordConfirmation) {
            passwordFlag.value = true
        } else {
            passwordFlag.value = false
        }
    })

    const passwordValidation = computed(() => {
        let re = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/i
        if (re.test(signupData.value.password)) {
            passwordValidFlag.value = true
        } else {
            passwordValidFlag.value = false
        }
    })
    

    return {
        goSignup,
        check,
        loginData,
        // store,
        login,
        message,
        signupData,
        signup,
        kakaologin,
        emailValidation,
        SignupEmailValidation,
        passwordCheck,
        passwordFlag,
        passwordValidation,
        passwordValidFlag,


    }
    }
}
</script>

<style scoped>
 @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

 * {
     margin: 0;
     padding: 0;
     box-sizing: border-box;
     font-family: 'Poppins', sans-serif;
     padding-left:8px;
 }

 body {
      background: linear-gradient(87deg,#2dce89,#2dcecc)!important;
      background: linear-gradient(87deg,#2dce89,#2dcecc)!important;
      background-image: linear-gradient(87deg,#2dce89,#2dcecc)!important;
      background-image: linear-gradient(87deg,#2dce89,#2dcecc)!important;
      padding: 0rem;
     /* min-height: 93vh */
 }

 .wrapper {
     max-width: 500px;
     margin: 50px auto
 }

 .wrapper .card {
     max-width: 400px;
     min-height: 450px;
     margin: 30px;
     background: rgba(255, 255, 255, 0.1);
     overflow: hidden;
     backdrop-filter: blur(10px);
     border: 1px solid rgba(255, 255, 255, 0.5);
     border-radius: 15px;
     /* cursor: pointer; */
     padding: 0.8rem
 }

 .wrapper .card a {
     text-decoration: none;
     color: #eee
 }

 .wrapper .card a:hover {
     color: #fff
 }

 .wrapper .card .input-field {
     border: 1px solid #ddd;
     border-radius: 5px;
     color: #eee;
     padding: 0.3rem
 }

 .wrapper .card .input-field input {
     background-color: inherit
 }

 .wrapper .card .input-field input.form-control,
 .wrapper .card .input-field input.form-control:focus {
     border: none;
     outline: none;
     box-shadow: none;
     color: #eee
 }

 .wrapper .card .input-field button.btn {
     color: #eee;
     padding: 0rem;
     padding-right: 0.5rem
 }

 .wrapper .card .input-field button.btn:hover {
     color: #fff
 }

 .wrapper .card .input-field button.btn:focus {
     border: none;
     outline: none;
     box-shadow: none
 }

 .wrapper .card .input-field input::placeholder {
     color: #eee
 }

 .wrapper .card .option {
     display: block;
     position: relative;
     padding-left: 25px;
     cursor: pointer;
     user-select: none
 }

 .wrapper .card .option span.text-light-white:hover {
     color: #fff
 }

 .wrapper .card .option input {
     position: absolute;
     opacity: 0;
     cursor: pointer;
     height: 0;
     width: 0
 }

 .checkmark {
     position: absolute;
     top: 3px;
     left: 0;
     height: 18px;
     width: 18px;
     background-color: #fff;
     border-radius: 2px
 }

 .wrapper .card .option:hover input~.checkmark {
     background-color: #f1f1f1
 }

 .wrapper .card .option input:checked~.checkmark {
     border: none;
     background-color: #333;
     transition: 300ms ease-in-out all
 }

 .checkmark:after {
     content: "\2713";
     position: absolute;
     display: none;
     color: #fff;
     font-size: 1rem
 }

 .wrapper .card .option input:checked~.checkmark:after {
     display: block
 }

 .wrapper .card .option .checkmark:after {
     left: 3px;
     top: -3px;
     width: 5px;
     height: 10px
 }

 .wrapper .card .btn.btn-primary {
     border-radius: 20px;
     width: 100px;
     background-color: #fff;
     color: #333;
     border: none
 }

 .wrapper .card .btn.btn-primary:hover {
     color: #fff;
     background: #333
 }

 .wrapper .card .btn.btn-primary:focus {
     border: none;
     box-shadow: none
 }

 .wrapper .card .text-light-white {
     color: #ddd
 }

 .wrapper .card .line span.connect {
     position: absolute;
     top: -12px;
     left: 33%;
     color: #000;
     padding: 0 0.3rem;
     z-index: 100;
     border-radius: 2px;
     background-color: #fff
 }

 .wrapper .card .connections a img {
     width: 40px;
     height: 40px;
     border-radius: 50%;
     object-fit: cover
 }

 @media(max-width: 370px) {
     .wrapper .card .line:after {
         left: 27%
     }
 }

 @media(max-width: 350px) {
     .wrapper {
         margin: 10px auto
     }

     .wrapper .card {
         margin: 10px
     }
 }






.btr {
    border-top-right-radius: 5px !important
}

.btl {
    border-top-left-radius: 5px !important
}

.btn-dark {
    color: #fff;
    background-color: #0d6efd;
    border-color: #0d6efd
}

.btn-dark:hover {
    color: #fff;
    background-color: #0d6efd;
    border-color: #0d6efd
}

.nav-pills {
    display: table !important;
    width: 100%
}

.nav-pills .nav-link {
    border-radius: 0px;
    border-bottom: 1px solid #0d6efd40
}

.nav-item {
    display: table-cell;
    background: #0d6efd2e
}

.form {
    padding: 10px;
    height: 300px
}


.form input {
    margin-bottom: 12px;
    border-radius: 3px
}

.form input:focus {
    box-shadow: none
}

.form button {
    margin-top: 20px
}
</style>