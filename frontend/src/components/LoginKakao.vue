<template>
  <div  class="btn_type2 btn_kakao">
    
    <img
        class="kakao_btn"
        src="@/assets/kakaoimage.png"
        @click="loginWithKakao"

    />
  </div>
</template>

<script>
import api from "../services/api"
export default {
    name: "LoginKakao",
  
    methods: {
        loginWithKakao() {
          const vm = this
          if (!window.Kakao.isInitialized()) {
              // JavaScript key를 인자로 주고 SDK 초기화
              window.Kakao.init('eb6b89b350284c15c748ea3d3855f6c5');
              //             Kakao.API.request({
              //   url: '/v1/api/talk/channels',
              //   success: function(response) {
              //     console.log(response);
              //   },
              //   fail: function(error) {
              //     console.log(error);
              //   }
              // });
              // SDK 초기화 여부를 확인하자.
              console.log(window.Kakao.isInitialized());
              }
          
          // if (window.Kakao.Auth.getAccessToken()) {
          //   window.Kakao.API.request({
          //     url: '/v1/user/unlink',
          //     success: function (response) {
          //       console.log(response)
          //     },
          //     fail: function (error) {
          //       console.log(error)
          //     },
          //   })
          //   window.Kakao.Auth.setAccessToken(undefined)
          // }
          window.Kakao.Auth.login({
          success: function () {
            window.Kakao.API.request({
              url: '/v2/user/me',
              // data: {
              //   property_keys: ["kakao_account.email","kakao_account.age_range","properties.nickname","properties.profile_image","properties.thumbnail_image","kakao_account.birthday","kakao_account.gender"]
              // },
              success: async function (response) {
                const a = {
                  username : response.properties.nickname,
                  password : 'a'+response.id+'!',
                  email : response.kakao_account.email,
                  roles : ['ROLE_USER']
                }

                console.log(a)
                // console.log(response)
                // this.schema['username'] = response.id,
                // this.schema['password'] = response.id,
                // this.schema['email'] =  response.kakao_account.email,
                
                // console.log(this.schema)
                api.post("/auth/signup",a)
                .then(res => {
                  console.log(res)
                  const b = response.id
                  vm.$store.dispatch("auth/login", {email:b,password:b})
                  // vm.$router.push("/");
                })
                .catch(err => {
                  console.log(err)
                  
                //   const b = response.id
                //   // async 해보기
                  vm.$store.dispatch("auth/login", {email:response.kakao_account.email,password:'a'+response.id+'!'})
                  console.log('ewf' ,b,b)
                  vm.$router.push("/main");

                })
              },
              fail: function (error) {
                console.log(error)
              },
            })
          },
          fail: function (error) {
            console.log(error)
          },
          })






      },
    },
};
</script>
<style scoped>

.kakao_btn {
    cursor: pointer;
    border: none;
    background: no-repeat center left 10px;
    background-size: 40px;
    display: block;
    width: 100%;
    font-size: 1.125rem;
    line-height: 54px;
    letter-spacing: -0.5px;
    font-weight: 400;
    margin-bottom: 20px;
    height:54px;

}

.btn_type2 .btn_kakao {
    border: none;
    background: no-repeat center left 10px;
    background-size: 40px;
    cursor: pointer;
}

.btn_type2 {
    display: block;
    width: 100%;
    font-size: 1.125rem;
    line-height: 54px;
    letter-spacing: -0.5px;
    font-weight: 400;
    text-align: center;
    margin-bottom: 20px;
}
</style>