<template>
  <!-- <h2>거래처 관리 페이지</h2> -->
  <div class="wrapper">
    <!-- 내용 들어갈 곳 -->
    <div class="main-content">
      <sidebar/>
      <div class="header">
        <p class="head_title">거래처 관리</p>
      </div>
      <div class="content_outside_box">
        <!-- <div class="content_box"> -->
          <div class="container-fluid mt-7">
            <!-- 템플릿 테이블 시작 -->
            <div class="col">
              <div class="card shadow">
                <div class="card-header border-0 my-2 d-flex" style="justify-content: space-between; align-content: center;">
                  <h3 class="mb-0 d-flex" style="align-items: center;">Partner</h3>

                  <!-- 브랜드 등록 모달 시작 -->
                  <div>
                    <span class="card__button btn createBtn" data-bs-toggle="modal" data-bs-target="#brand-modal" style="cursor:pointer;">거래처등록</span>
                  </div>

                  <!-- 모달 내용 -->
                  <b-modal class="modal fade" id="brand-modal" title="Brand" hide-footer @show="resetModal" @hidden="resetModal">
                      <b-card class="overflow-hidden" style="max-width: 540px;">
                        <!-- 이미지 부분 -->
                        <div class="d-flex" style="justify-content: center; align-content: center;">
                          <label for="fileName" class="join-profile-img-edit" >
                            <input ref="image" type="file" id="fileName" accept="image/*" @change="onInputImage" style="opacity: 0">
                            <div class="d-flex" style="justify-content: center; align-content: center;">
                              <div class="d-flex" v-if="brandInfo.previewImg" style="height:250px; ">
                                <img :src="brandInfo.previewImg" :state="modalState.imgState" alt="" class="aa" style="width: 250px; heigth: 200px;">
                              </div>
                              <div v-else style="height:250px; ">
                                <img src="@/assets/uploadicon.jpg" alt="" style="width: 250px; height: 200px;">
                              </div>
                            </div>
                          </label>
                        </div>
                        <!-- 이미지 등록 부분 끝-->
                        
                        <b-card-text class="mt-4">
                          <div>
                            <div class="form-tag" style="width: 100%;">
                              <b-form-input class="input_tag" type="text" v-model="brandInfo.name" :state="modalState.brandNameState" placeholder=" 브랜드명" style="text-transform: uppercase;"></b-form-input>
                              <b-form-input class="input_tag my-3" type="date" v-model="brandInfo.endDate" :state="modalState.endDateState" placeholder=" 계약 만료 일자"></b-form-input>
                              <b-form-input class="input_tag" type="text" v-model="brandInfo.address" :state="modalState.addressState" placeholder=" 지갑주소"></b-form-input>
                            </div>
                          </div>
                        </b-card-text>
                      </b-card>

                    <div class="modal-footer">
                      <button type="button" class="btn transeferBtn" data-bs-dismiss="modal" block @click="createBrand">등록</button>
                      <button type="button" class="btn cancleBtn" style="width: 70px" data-bs-dismiss="modal" block>취소</button>
                    </div>
                  </b-modal>
                  <!-- 브랜드 등록 모달 끝 -->
                  

                </div>
                <div class="table-responsive">
                  <table class="table align-items-center table-flush">
                    <thead class="thead-light">
                      <tr>
                        <th style="width: 10%;">이미지</th>
                        <th style="width: 15%;">브랜드명</th>
                        <th style="width: 5%;">Admin</th>
                        <th style="width: 5%;">계약 만료일</th>
                        <th style="width: 5%;">관리</th>
                      </tr>
                    </thead>
                    <tbody 
                      id="my-table" 
                      :items="brandItems.value" 
                      :per-page="perPage" 
                      :current-page="currentPage" 
                      v-for="(branditem, idx) in brandItems" 
                      :key="idx"
                    >
                      <tr>
                        <th scope="row">
                          <div class="media align-items-center">
                            <div class="media-body" style="text-align: center;">
                              <!-- <span class="badge badge-dot mr-4">
                                <i style="background-color: #3adacf;"></i>
                              </span> -->
                              <span class="mb-0 text-sm"><img :src="branditem.imageUrl" alt="logoimg" style="width: 60px;"></span>
                              <!-- <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Dior_Logo.svg/1200px-Dior_Logo.svg.png" alt=""> -->
                            </div>
                          </div>
                        </th>

                        <td>
                          <p class="mb-0 notice-title" style="font-size: 1rem;">{{ branditem.name }}</p>
                        </td>
                        <td>
                          <div class="avatar-group">
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Ryan Tompson">
                              <img alt="Image placeholder" src="@/assets/광.jpg" class="rounded-circle">
                            </a>
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Romina Hadid">
                              <img alt="Image placeholder" src="@/assets/동.jpg" class="rounded-circle">
                            </a>
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Alexander Smith">
                              <img alt="Image placeholder" src="@/assets/현.jpg" class="rounded-circle">
                            </a>
                            <a href="#" class="avatar avatar-sm" data-toggle="tooltip" data-original-title="Jessica Doe">
                              <img alt="Image placeholder" src="@/assets/호.jpg" class="rounded-circle">
                            </a>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span class="mr-2">{{ makeDate(branditem.endDate) }}</span>
                          </div>
                        </td>
                        <td>
                          <b-button type="button" class="btn deleteBtn2" @click="deletePartner(branditem.brandId)">계약해지</b-button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              <!-- 페이지네이션 -->
              <div class="card-footer py-4">
                <b-pagination
                  @click="pageClick"
                  v-model="currentPage"
                  :total-rows="rowws"
                  :per-page="perPage"
                  aria-controls="my-table"
                  align="end"
                ></b-pagination>
              </div>
              <!-- 페이지네이션 끝 -->
            </div>
            <!-- </div> -->
            <div class="createBtn_position">
            <!-- <button type="button" class="btn createBtn" @click="goNoticeForm" >글생성</button> -->
          </div>
          </div>
        
        </div>
      </div>
    </div>
      <!-- 내용 들어갈 곳 끝 -->
  </div>
</template>

<script>
import "@/assets/style/notice/noticeTable.css"
import "@/assets/style/notice/table.css"

import Sidebar from '@/components/Sidebar.vue'

import { useRouter } from 'vue-router'
import api from "@/services/api.js"
import { onMounted, ref } from 'vue';
import axios from 'axios'

export default {
  name: 'PartnerManagement',
  components: {
    Sidebar,
  },
  setup() {
    const router = useRouter()
    const brandItems = ref(null)
    const currentPage = ref(null)
    const rowws = ref(null)
    const perPage = ref(null)
    const noticeId = ref(null)

    const brandInfo = ref({
      name: null,
      endDate : null,
      address : null,
      imageUrl: null,
      previewImg: null,
    })

    const brandImg = ref([])
    console.log(brandImg.value, '⭐⭐⭐⭐⭐⭐⭐')

    function createPartner() {
      router.push({name: 'PartnerCreate'})
    }

    const brandLogoImg = ref(null)
    const brandImgFile = ref(null)


    // 이미지 업로드
    const onInputImage = (event) => {
      // 이미지 파일 brandImg에 담는 것
      if( event.target.files && event.target.files.length > 0 ) {
        console.log(event, '첨부 확인')
        brandImgFile.value = event.target.files[0];
        brandLogoImg.value = URL.createObjectURL(brandImgFile.value);
        
        // 이미지 미리보기 부분
        brandInfo.value.previewImg = brandLogoImg.value
        
        console.log(brandLogoImg.value, 'brandImg.value 확인')
        console.log(brandImgFile.value, 'brandImgFile.value 확인')
        console.log(brandInfo.value.previewImg, 'brandInfo.value.previewImg 확인')
      }
      brandLogoImg.value = event.target.files[0];
      console.log(brandLogoImg.value, 'brandImg.value 확인')
    }

    // 브랜드 조회 & 페이지네이션
    const getBrand = () => {
      api.get('/brand')
      .then((res) => {
        console.log(res, 'res 확인')
        brandItems.value = res.data.content
        console.log(brandItems.value, '브랜드 목록 확인')

        currentPage.value = res.data.pageable['pageNumber']
        console.log(currentPage.value, 'currentPage 확인')

        rowws.value = res.data.totalElements
        console.log(rowws.value, 'rowws 전체 개수')

        perPage.value = res.data.pageable['pageSize']
        console.log(perPage.value, 'perP 확인')

      })
    }

    // 거래처 등록
    const createBrand = () => {
      const metadata = {
        name: brandInfo.value.name,
        endDate : brandInfo.value.endDate,
        address : brandInfo.value.address,
      }

    const formData = new FormData();
      formData.append('metadata', new Blob([JSON.stringify(metadata)] , {type: "application/json"}));
      formData.append('image', brandLogoImg.value);
      console.log(formData, 'formData 확인')  
        
      console.log(brandInfo.value, '브랜드 확인')
      api.post('/brand', formData)
      .then((res) => {
        console.log(res)
        // 등록하고 바로 불러오기
        getBrand()
      }) 
    }


    // 모달 초기화
    const modalState = ref({
      brandNameState : null,
      endDateState : null,
      addressState : null,
      imgState: null,
    })

    // 모달 초기화
    const resetModal = () => {
        brandInfo.value.name = null
        brandInfo.value.endDate = null
        brandInfo.value.address = null
        brandInfo.value.previewImg = null
        modalState.value.brandNameState = null
        modalState.value.endDateState = null
        modalState.value.addressState = null
        modalState.value.imgState = null
      }


    // 버튼 누르면 페이지 변경
    const pageClick = () => {
      console.log(currentPage.value, 'currentPage 바뀌는지 확인')
      axios({
        method: 'get',
        url: `${도메인}/api/v1/brand?page=${currentPage.value}&size=10`
      })
      .then((res) => {
        console.log(res,'👍페이지확인')
        brandItems.value = res.data.content
      })
    }

    // 브랜드 삭제
    const deletePartner = (brandNum) => {
      const brandInfo = {
        brandId: brandNum
      }
      console.log(brandInfo.brandId, '번호 확인')
      axios({
        method: 'delete',
        url: `${도메인}/api/v1/brand`,
        data: {brandId: brandInfo.brandId}
      })
      .then(() => {
        console.log(brandInfo.brandId,'브랜드 삭제 확인')
        getBrand()
      })
    }

    


    // 등록일 슬라이싱
    const makeDate = (datetime) => {
      const old = ''+datetime
      return old.substring(0, 10)
    }

    onMounted(() => {
      getBrand()
      brandItems
    })

    return {
      createPartner,
      brandItems,
      currentPage,
      rowws,
      perPage,
      noticeId,
      pageClick,
      deletePartner,
      getBrand,
      makeDate,
      brandImg,

      brandInfo,
      createBrand,

      brandImg,
      brandImgFile,
      onInputImage,
      resetModal,
      modalState,
    }
  }
}
</script>

<style scoped>

/* 모달 부분 */
.input_tag {
  width: 90%;
  height: 40px;
  background-color: #e6f5f4 !important;
  border: none !important;
  font-size: 0.8rem;
  margin: auto;
}

.form-tag {
  position: relative;
  /* margin: auto; */
}

/* 모달 부분 끝 */

table {
  /* position: relative; */
  border-top: none;
}

thead {
  /* display: flex; */
  /* margin-top: ; */
  /* border-top: 1px solid #333333; */
  border-bottom: 1px solid #333333;
}

.deleteBtn2 {
  font-size: 0.8rem !important;
  border: none !important;
  height: 30px;
  width: 65px;
  text-align: center !important;
  padding: 0 !important;
}

.deleteBtn2:hover {
  border: none !important;
  box-shadow: none !important;
  background-color: #2dce89 ;
  color: white ;
}

.backBtn:hover {
background-color: #727171 !important;
}

/* 테이블 css */
.content_outside_box {
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #e9ecef;
  text-align: left;
  box-sizing: border-box;
  width: 100%;
  margin-left: auto;
  margin-top: -4.5rem!important;
  padding-left: 70px!important;
  padding-right: 70px!important;
}

.notice-title {
  width: 50%; 
  text-overflow: ellipsis; 
  overflow: hidden; 
  white-space: wrap;
  position: relative;
  text-align: left;
  left: 20%;
}

.container-fluid {
  position: relative;
  right: 4px
}

* {
  font-family: 'SUIT-Regular';
}

/* 크기 클 때 */
/* .head_title {
  color: white;
  display: flex;
  margin-left: 80px;
  margin-top: 30px;
} */

/* 크기 작을 때 */
.head_title {
  color: white;
  position:absolute; 
  margin-left:100px; 
  margin-top: 50px;
}

#my-table:hover {
  cursor: pointer;
  transition: all 0.2s ease 0s;
  box-shadow: rgb(4 17 29 / 25%) 0px 0px 8px 0px;
  background-color: rgb(251, 253, 255);
}

/* noticeSide css */
* {
  font-family: 'MinSans-Regular';
}

.nav-link {
  text-align: left;
  font-size: 17px;
  margin-bottom: 15px;
}

.nav-link:hover {
  background-color: #2dce89;
}

.header {
  display: flex;
  position: relative;
  background-color: #2dce89;
  height: 380px;
  left: 7px;
  padding-bottom: 72px;
}

.main-content {
  position: relative;
  background-color: #f5f6fc;
  /* top: 50px; */
  /* height: 100%; */
}

.nav-link-text {
  /* color: white; */
  color: black;
  font-size: 24px;
  margin-left: 40px;
  font-family: 'MinSans-Regular';
}

.nav-link-text:hover {
  color: black;
  cursor: pointer;
}

.content_outside_box {
  position: relative;
  bottom: 200px;
  left: 40px;
}

.content_box {
  width: 50%;
  height: 600px;
  background-color: white;
  margin: auto;
  border: 1px;
  border-radius: 10px;
  box-shadow: 3px 3px 10px 1px #d8d7d7;
}

.pagination_outside {
  display: flex;
  position: relative;
  bottom: 130px;
  /* left: 150px; */
}

.pagination {
  margin: auto;
}

.createBtn {
  background-color: #fff !important;
  height: 40px;
  color: #333333;
  border-color: transparent;
  border: 1px solid transparent !important;
  box-shadow: 1px 1px 2px 2px #ececf0;
  right: 30%;
  /* background-image: linear-gradient( 135deg, #90F7EC 10%, #32CCBC 100%); */
}

.createBtn:hover {
  background-color: #32CCBC !important;
  color: white;
}


/* 테이블 디자인 */
*,
*::before,
*::after {
  box-sizing: border-box;
}

* {
  font-family: 'SUIT-Regular';
}

@-ms-viewport {
  width: device-width;
}

figcaption,
footer,
main,
nav,
section {
  display: block;
}

body {
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  margin: 0;
  color: #525f7f;
  background-color: #f8f9fe;
}

[tabindex='-1']:focus {
  outline: 0 !important;
}

h2,
h3 {
  margin-top: 0;
  margin-bottom: .5rem;
}

p {
  margin-top: 0;
  margin-bottom: 1rem;
}

ul {
  margin-top: 0;
  margin-bottom: 1rem;
}

ul ul {
  margin-bottom: 0;
}

dfn {
  font-style: italic;
}

strong {
  font-weight: bolder;
}

a {
  text-decoration: none;
  color: #5e72e4;
  background-color: transparent;
  -webkit-text-decoration-skip: objects;
}

a:hover {
  text-decoration: none;
  color: #233dd2;
}

a:not([href]):not([tabindex]) {
  text-decoration: none;
  color: inherit;
}

a:not([href]):not([tabindex]):hover,
a:not([href]):not([tabindex]):focus {
  text-decoration: none;
  color: inherit;
}

a:not([href]):not([tabindex]):focus {
  outline: 0;
}

img {
  vertical-align: middle;
  border-style: none;
}

table {
  border-collapse: collapse;
}

.table>:not(:first-child) {
  border-top: None !important;
}

caption {
  padding-top: 1rem;
  padding-bottom: 1rem;
  caption-side: bottom;
  text-align: left;
  color: #8898aa;
}

th {
  text-align: inherit;
}

button {
  border-radius: 0;
}

button:focus {
  outline: 1px dotted;
  outline: 5px auto -webkit-focus-ring-color;
}

input,
button {
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
  margin: 0;
}

button,
input {
  overflow: visible;
}

button {
  text-transform: none;
}

button,
[type='reset'],
[type='submit'] {
  -webkit-appearance: button;
}

button::-moz-focus-inner,
[type='button']::-moz-focus-inner,
[type='reset']::-moz-focus-inner,
[type='submit']::-moz-focus-inner {
  padding: 0;
  border-style: none;
}

input[type='radio'],
input[type='checkbox'] {
  box-sizing: border-box;
  padding: 0;
}

input[type='date'],
input[type='time'],
input[type='datetime-local'],
input[type='month'] {
  -webkit-appearance: listbox;
}

legend {
  font-size: 1.5rem;
  line-height: inherit;
  display: block;
  width: 100%;
  max-width: 100%;
  margin-bottom: .5rem;
  padding: 0;
  white-space: normal;
  color: inherit;
}

progress {
  vertical-align: baseline;
}

[type='number']::-webkit-inner-spin-button,
[type='number']::-webkit-outer-spin-button {
  height: auto;
}

[type='search'] {
  outline-offset: -2px;
  -webkit-appearance: none;
}

[type='search']::-webkit-search-cancel-button,
[type='search']::-webkit-search-decoration {
  -webkit-appearance: none;
}

::-webkit-file-upload-button {
  font: inherit;
  -webkit-appearance: button;
}

[hidden] {
  display: none !important;
}

h2,
h3,
.h2,
.h3 {
  font-family: inherit;
  font-weight: 600;
  line-height: 1.5;
  margin-bottom: .5rem;
  color: #32325d;
}

h2,
.h2 {
  font-size: 1.25rem;
}

h3,
.h3 {
  font-size: 1.0625rem;
}

.container {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  padding-right: 15px;
  padding-left: 15px;
}

@media (min-width: 576px) {
  .container {
      max-width: 540px;
  }
}

@media (min-width: 768px) {
  .container {
      max-width: 720px;
  }
}

@media (min-width: 992px) {
  .container {
      max-width: 960px;
  }
}

@media (min-width: 1200px) {
  .container {
      max-width: 1140px;
  }
}

.row {
  display: flex;
  margin-right: -15px;
  margin-left: -15px;
  flex-wrap: wrap;
}

.col,
.col-xl-6 {
  position: relative;
  width: 100%;
  min-height: 1px;
  padding-right: 15px;
  padding-left: 15px;
}

.col {
  max-width: 100%;
  flex-basis: 0;
  flex-grow: 1;
}

@media (min-width: 1200px) {

  .col-xl-6 {
      max-width: 50%;
      flex: 0 0 50%;
  }
}

.table {
  width: 100%;
  margin-bottom: 1rem;
  background-color: transparent;
}

.table th,
.table td {
  padding: 1rem;
  vertical-align: top;
  /* border-top: 1px solid #e9ecef !important; */
}

.table tr {
  border-bottom: 1px solid #e9ecef !important;

}
.table thead th {
  vertical-align: bottom;
  /* border-bottom: 1px solid #e9ecef !important; */
  padding-top: 0 !important;
  padding-bottom: 0 !important;
  height: 40px;
}

.table tbody + tbody {
  /* border-top: 1px solid #e9ecef !important; */
}

.table .table {
  background-color: #f8f9fe !important;
}


.table .thead-light th {
  color: #8898aa;
  border-color: #e9ecef;
  background-color: white !important;
}


.table-responsive {
  display: block;
  overflow-x: auto;
  width: 100%;
  -webkit-overflow-scrolling: touch;
  -ms-overflow-style: -ms-autohiding-scrollbar;
}

.btn {
  font-size: 1rem;
  font-weight: 600;
  line-height: 1.5;
  display: inline-block;
  padding: .625rem 1.25rem;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  transition: color .15s ease-in-out, background-color .15s ease-in-out, border-color .15s ease-in-out, box-shadow .15s ease-in-out;
  text-align: center;
  vertical-align: middle;
  white-space: nowrap;
  border: 1px solid transparent;
  border-radius: .375rem;
}

@media screen and (prefers-reduced-motion: reduce) {
  .btn {
      transition: none;
  }
}

.btn:hover,
.btn:focus {
  text-decoration: none;
}

.btn:focus {
  outline: 0;
  box-shadow: 0 7px 14px rgba(50, 50, 93, .1), 0 3px 6px rgba(0, 0, 0, .08);
}

.btn.disabled,
.btn:disabled {
  opacity: .65;
  box-shadow: none;
}

.btn:not(:disabled):not(.disabled) {
  cursor: pointer;
}

.btn:not(:disabled):not(.disabled):active,
.btn:not(:disabled):not(.disabled).active {
  box-shadow: none;
}

.btn:not(:disabled):not(.disabled):active:focus,
.btn:not(:disabled):not(.disabled).active:focus {
  box-shadow: 0 7px 14px rgba(50, 50, 93, .1), 0 3px 6px rgba(0, 0, 0, .08), none;
}

a.btn.disabled {
  pointer-events: none;
}

.btn-sm {
  font-size: .875rem;
  line-height: 1.5;
  padding: .25rem .5rem;
  border-radius: .375rem;
}

.dropdown {
  position: relative;
}

.dropdown-menu {
  font-size: 1rem;
  position: absolute;
  z-index: 1000;
  top: 100%;
  left: 0;
  display: none;
  float: left;
  min-width: 10rem;
  margin: .125rem 0 0;
  padding: .5rem 0;
  list-style: none;
  text-align: left;
  color: #525f7f;
  border: 0 solid rgba(0, 0, 0, .15);
  border-radius: .4375rem;
  background-color: #fff;
  background-clip: padding-box;
  box-shadow: 0 50px 100px rgba(50, 50, 93, .1), 0 15px 35px rgba(50, 50, 93, .15), 0 5px 15px rgba(0, 0, 0, .1);
}

.dropdown-menu-right {
  right: 0;
  left: auto;
}

.dropdown-menu[x-placement^='top'],
.dropdown-menu[x-placement^='right'],
.dropdown-menu[x-placement^='bottom'],
.dropdown-menu[x-placement^='left'] {
  right: auto;
  bottom: auto;
}

.dropdown-item {
  font-weight: 400;
  display: block;
  clear: both;
  width: 100%;
  padding: .25rem 1.5rem;
  text-align: inherit;
  white-space: nowrap;
  color: #212529;
  border: 0;
  background-color: transparent;
}

.dropdown-item:hover,
.dropdown-item:focus {
  text-decoration: none;
  color: #16181b;
  background-color: #f6f9fc;
}

.dropdown-item.active,
.dropdown-item:active {
  text-decoration: none;
  color: #fff;
  background-color: #5e72e4;
}

.dropdown-item.disabled,
.dropdown-item:disabled {
  color: #8898aa;
  background-color: transparent;
}

.nav {
  display: flex;
  margin-bottom: 0;
  padding-left: 0;
  list-style: none;
  flex-wrap: wrap;
}

.card {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  border: none !important;
  border-radius: .375rem;
  background-color: #fff;
  background-clip: border-box;
  margin-bottom: 30px;
}

.card-header {
  margin-bottom: 0;
  padding: 1.25rem 1.5rem;
  border-bottom: 1px solid rgba(0, 0, 0, .05);
  background-color: #fff !important;
  text-align: left;
  font-size: 1.0625rem;
}

.card-header:first-child {
  border-radius: calc(.375rem - 1px) calc(.375rem - 1px) 0 0;
}

.card-footer {
  padding: 1.25rem 1.5rem;
  border-top: none !important;
  background-color: #fff !important;
}

.card-footer:last-child {
  border-radius: 0 0 calc(.375rem - 1px) calc(.375rem - 1px);
}

.pagination {
  display: flex;
  padding-left: 0;
  list-style: none;
  border-radius: .375rem;
  height: 52px;
}



.badge {
  font-size: 66%;
  font-weight: 600;
  line-height: 1;
  display: inline-block;
  padding: .35rem .375rem;
  text-align: center;
  vertical-align: baseline;
  white-space: nowrap;
  border-radius: .375rem;
}

.badge:empty {
  display: none;
}

.btn .badge {
  position: relative;
  top: -1px;
}

@keyframes progress-bar-stripes {
  from {
      background-position: 1rem 0;
  }

  to {
      background-position: 0 0;
  }
}

.progress {
  font-size: .75rem;
  display: flex;
  overflow: hidden;
  height: 1rem;
  border-radius: .375rem;
  background-color: #e9ecef;
  box-shadow: inset 0 .1rem .1rem rgba(0, 0, 0, .1);
}

.progress-bar {
  display: flex;
  flex-direction: column;
  transition: width .6s ease;
  text-align: center;
  white-space: nowrap;
  color: #fff;
  background-color: #5e72e4;
  justify-content: center;
}

@media screen and (prefers-reduced-motion: reduce) {
  .progress-bar {
      transition: none;
  }
}

.media {
  display: flex;
  align-items: flex-start;
}

.media-body {
  flex: 1 1;

}

.tooltip {
  font-family: Open Sans, sans-serif;
  font-size: .875rem;
  font-weight: 400;
  font-style: normal;
  line-height: 1.5;
  position: absolute;
  z-index: 1070;
  display: block;
  margin: 0;
  text-align: left;
  text-align: start;
  white-space: normal;
  text-decoration: none;
  letter-spacing: normal;
  word-spacing: normal;
  text-transform: none;
  word-wrap: break-word;
  word-break: normal;
  opacity: 0;
  text-shadow: none;
  line-break: auto;
}

.tooltip.show
{
  opacity: .9;
}
.tooltip .arrow
{
  position: absolute;

  display: block;

  width: .8rem;
  height: .4rem;
}
.tooltip .arrow::before
{
  position: absolute;

  content: '';

  border-style: solid;
  border-color: transparent;
}

.bs-tooltip-top,
.bs-tooltip-auto[x-placement^='top']
{
  padding: .4rem 0;
}
.bs-tooltip-top .arrow,
.bs-tooltip-auto[x-placement^='top'] .arrow
{
  bottom: 0;
}
.bs-tooltip-top .arrow::before,
.bs-tooltip-auto[x-placement^='top'] .arrow::before
{
  top: 0;

  border-width: .4rem .4rem 0;
  border-top-color: #000;
}

.bs-tooltip-right,
.bs-tooltip-auto[x-placement^='right']
{
  padding: 0 .4rem;
}
.bs-tooltip-right .arrow,
.bs-tooltip-auto[x-placement^='right'] .arrow
{
  left: 0;

  width: .4rem;
  height: .8rem;
}
.bs-tooltip-right .arrow::before,
.bs-tooltip-auto[x-placement^='right'] .arrow::before
{
  right: 0;

  border-width: .4rem .4rem .4rem 0;
  border-right-color: #000;
}

.bs-tooltip-bottom,
.bs-tooltip-auto[x-placement^='bottom']
{
  padding: .4rem 0;
}
.bs-tooltip-bottom .arrow,
.bs-tooltip-auto[x-placement^='bottom'] .arrow
{
  top: 0;
}
.bs-tooltip-bottom .arrow::before,
.bs-tooltip-auto[x-placement^='bottom'] .arrow::before
{
  bottom: 0;

  border-width: 0 .4rem .4rem;
  border-bottom-color: #000;
}

.bs-tooltip-left,
.bs-tooltip-auto[x-placement^='left']
{
  padding: 0 .4rem;
}
.bs-tooltip-left .arrow,
.bs-tooltip-auto[x-placement^='left'] .arrow
{
  right: 0;

  width: .4rem;
  height: .8rem;
}
.bs-tooltip-left .arrow::before,
.bs-tooltip-auto[x-placement^='left'] .arrow::before
{
  left: 0;

  border-width: .4rem 0 .4rem .4rem;
  border-left-color: #000;
}

.tooltip-inner
{
  max-width: 200px;
  padding: .25rem .5rem;

  text-align: center;

  color: #fff;
  border-radius: .375rem;
  background-color: #000;
}

.bg-success {
  background-color: #2dce89 !important;
}

a.bg-success:hover,
a.bg-success:focus,
button.bg-success:hover,
button.bg-success:focus {
  background-color: #24a46d !important;
}

.bg-info {
  background-color: #3adacf !important;
}

a.bg-info:hover,
a.bg-info:focus,
button.bg-info:hover,
button.bg-info:focus {
  background-color: #0da5c0 !important;
}

.bg-warning {
  background-color: #fb6340 !important;
}

a.bg-warning:hover,
a.bg-warning:focus,
button.bg-warning:hover,
button.bg-warning:focus {
  background-color: #fa3a0e !important;
}

.bg-danger {
  background-color: #f5365c !important;
}

a.bg-danger:hover,
a.bg-danger:focus,
button.bg-danger:hover,
button.bg-danger:focus {
  background-color: #ec0c38 !important;
}

.bg-default {
  background-color: #172b4d !important;
}

a.bg-default:hover,
a.bg-default:focus,
button.bg-default:hover,
button.bg-default:focus {
  background-color: #0b1526 !important;
}

.bg-transparent {
  background-color: transparent !important;
}

.border-0 {
  border: 0 !important;
}

.rounded-circle {
  border-radius: 50% !important;
  width: 36px;
  height: 36px;
}

.d-flex {
  display: flex !important;
}

.justify-content-end {
  justify-content: flex-end !important;
}

.align-items-center {
  align-items: center !important;
}

@media (min-width: 1200px) {

  .justify-content-xl-between {
      justify-content: space-between !important;
  }
}

.sr-only {
  position: absolute;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  width: 1px;
  height: 1px;
  padding: 0;
  white-space: nowrap;
  border: 0;
}

.shadow {
  box-shadow: 0 0 2rem 0 rgba(136, 152, 170, .15) !important;
}

.mb-0 {
  margin-bottom: 0 !important;
}

.mr-2 {
  margin-right: .5rem !important;
}

.mr-3 {
  margin-right: 1rem !important;
}

.mr-4 {
  margin-right: 1.5rem !important;
}

.mt-5 {
  margin-top: 3rem !important;
}

.mb-5 {
  margin-bottom: 3rem !important;
}

.mt-7 {
  margin-top: 6rem !important;
}

.py-4 {
  padding-top: 1.5rem !important;
}

.py-4 {
  padding-bottom: 1.5rem !important;
}

.m-auto {
  margin: auto !important;
}

.text-right {
  text-align: right !important;
}

.text-center {
  text-align: center !important;
}

.text-white {
  color: #fff !important;
}

.text-light {
  color: #adb5bd !important;
}

a.text-light:hover,
a.text-light:focus {
  color: #919ca6 !important;
}

.text-white {
  color: #fff !important;
}

a.text-white:hover,
a.text-white:focus {
  color: #e6e6e6 !important;
}

@media print {
  *,
*::before,
*::after {
      box-shadow: none !important;
      text-shadow: none !important;
  }

  a:not(.btn) {
      text-decoration: underline;
  }

  thead {
      display: table-header-group;
  }

  tr,
img {
      page-break-inside: avoid;
  }

p,
h2,
h3 {
      orphans: 3;
      widows: 3;
  }

  h2,
h3 {
      page-break-after: avoid;
  }

@page {
      size: a3;
  }

  body {
      min-width: 992px !important;
  }

  .container {
      min-width: 992px !important;
  }

  .badge {
    border: 1px solid #000;
  }

  .table {
    border-collapse: separate !important;
  }

.table td,
.table th {
    background-color: #fff !important;
  }

}

figcaption,
main {
  display: block;
}

main {
  overflow: hidden;
}

@keyframes floating-lg {
  0% {
      transform: translateY(0px);
  }

  50% {
      transform: translateY(15px);
  }

  100% {
      transform: translateY(0px);
  }
}

@keyframes floating {
  0% {
      transform: translateY(0px);
  }

  50% {
      transform: translateY(10px);
  }

  100% {
      transform: translateY(0px);
  }
}

@keyframes floating-sm {
  0% {
      transform: translateY(0px);
  }

  50% {
      transform: translateY(5px);
  }

  100% {
      transform: translateY(0px);
  }
}

[class*='shadow'] {
  transition: all .15s ease;
}

.text-sm {
  font-size: .875rem !important;
}

.text-white {
  color: #fff !important;
}

a.text-white:hover,
a.text-white:focus {
  color: #e6e6e6 !important;
}

.text-light {
  color: #ced4da !important;
}

a.text-light:hover,
a.text-light:focus {
  color: #b1bbc4 !important;
}

.avatar {
  font-size: 1rem;
  display: inline-flex;
  width: 48px;
  height: 48px;
  color: #fff;
  border-radius: 50%;
  background-color: #adb5bd;
  align-items: center;
  justify-content: center;
}

.avatar img {
  width: 100%;
  border-radius: 50%;
}

.avatar-sm {
  font-size: .875rem;
  width: 36px;
  height: 36px;
}

.avatar-group .avatar {
  position: relative;
  z-index: 2;
  border: 2px solid #fff;
}

.avatar-group .avatar:hover {
  z-index: 3;
}

.avatar-group .avatar + .avatar {
  margin-left: -1rem;
}

.badge {
  text-transform: uppercase;
}

.badge a {
  color: #fff;
}

.btn .badge:not(:first-child) {
  margin-left: .5rem;
}

.btn .badge:not(:last-child) {
  margin-right: .5rem;
}

.badge-dot {
  font-size: .875rem;
  font-weight: 400;
  padding-right: 0;
  padding-left: 0;
  text-transform: none;
  background: transparent;
}

.badge-dot strong {
  color: #32325d;
}

.badge-dot i {
  display: inline-block;
  width: .375rem;
  height: .375rem;
  margin-right: .375rem;
  vertical-align: middle;
  border-radius: 50%;
}

.btn {
  font-size: .875rem;
  position: relative;
  transition: all .15s ease;
  letter-spacing: .025em;
  text-transform: none;
  will-change: transform;
}

.btn:hover {
  transform: translateY(-1px);
  box-shadow: 0 7px 14px rgba(50, 50, 93, .1), 0 3px 6px rgba(0, 0, 0, .08);
}

.btn:not(:last-child) {
  margin-right: .5rem;
}

.btn i:not(:first-child) {
  margin-left: .5rem;
}

.btn i:not(:last-child) {
  margin-right: .5rem;
}

.btn-sm {
  font-size: .75rem;
}

[class*='btn-outline-'] {
  border-width: 1px;
}

.btn-icon-only {
  width: 2.375rem;
  height: 2.375rem;
  padding: 0;
}

a.btn-icon-only {
  line-height: 2.5;
}

.btn-icon-only.btn-sm {
  width: 2rem;
  height: 2rem;
}

.main-content {
  position: relative;
  /* height: 100%; */
}

.dropdown {
  display: inline-block;
}

.dropdown-menu {
  min-width: 12rem;
}
.dropdown-menu.show
{
  display: block;
}


.dropdown-menu .dropdown-item {
  font-size: .875rem;
  padding: .5rem 1rem;
}

.dropdown-menu .dropdown-item > i {
  font-size: 1rem;
  margin-right: 1rem;
  vertical-align: -17%;
}

.dropdown-menu a.media > div:first-child {
  line-height: 1;
}

.dropdown-menu a.media p {
  color: #8898aa;
}

.dropdown-menu a.media:hover p {
  color: #172b4d !important;
}

.footer {
  padding: 2.5rem 0;
  background: #f7fafc;
}

.footer .copyright {
  font-size: .875rem;
}

@media (min-width: 768px) {

@keyframes show-navbar-dropdown {
      0% {
    transition: visibility .25s, opacity .25s, transform .25s;
      transform: translate(0, 10px) perspective(200px) rotateX(-2deg);
      opacity: 0;
  }

  100% {
      transform: translate(0, 0);
      opacity: 1;
  }
}

@keyframes hide-navbar-dropdown {
  from {
      opacity: 1;
  }

  to {
      transform: translate(0, 10px);
      opacity: 0;
  }
}
}

@keyframes show-navbar-collapse {
  0% {
      transform: scale(.95);
      transform-origin: 100% 0;
      opacity: 0;
  }

  100% {
      transform: scale(1);
      opacity: 1;
  }
}

@keyframes hide-navbar-collapse {
  from {
      transform: scale(1);
      transform-origin: 100% 0;
      opacity: 1;
  }

  to {
      transform: scale(.95);
      opacity: 0;
  }
}

.page-item.active .page-link {
  box-shadow: 0 7px 14px rgba(50, 50, 93, .1), 0 3px 6px rgba(0, 0, 0, .08);
}

.page-item .page-link,
.page-item span {
  font-size: .875rem;
  display: flex;
  width: 36px;
  height: 36px;
  margin: 0 3px;
  padding: 0;
  border-radius: 50% !important;
  align-items: center;
  justify-content: center;
}

.page-link {
  line-height: 1.25;
  position: relative;
  display: block;
  margin-left: -1px;
  padding: .5rem .75rem;
  color: #28b9b0d6 !important;
  border: 1px solid #dee2e6;
  background-color: #fff;
}

.page-link:hover {
  z-index: 2;
  text-decoration: none;
  color: #28b9b0d6;
  border-color: #dee2e6;
  background-color: #dee2e6;
}

.page-link:focus {
  z-index: 2;
  outline: 0;
  box-shadow: none;
}


.page-link:not(:disabled):not(.disabled) {
  cursor: pointer;
}

.page-item:first-child .page-link {
  margin-left: 0;
  border-top-left-radius: .375rem;
  border-bottom-left-radius: .375rem;
}

.page-item:last-child .page-link {
  border-top-right-radius: .375rem;
  border-bottom-right-radius: .375rem;
}

.page-item.active .page-link {
  z-index: 1;
}

.page-item.active .page-link {  
  background-color: #2dce89 !important;  
  border-color: #2dce89 !important;  
  color: #fff !important;
}

.page-item.disabled .page-link {
  cursor: auto;
  pointer-events: none;
  color: #8898aa;
  border-color: #dee2e6;
  background-color: #fff;
}

.progress {
  overflow: hidden;
  height: 8px;
  margin-bottom: 1rem;
  border-radius: .25rem;
  background-color: #e9ecef;
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, .1);
}

.progress .sr-only {
  font-size: 13px;
  line-height: 20px;
  left: 0;
  clip: auto;
  width: auto;
  height: 20px;
  margin: 0 0 0 30px;
}

.progress-bar {
  height: auto;
  border-radius: 0;
  box-shadow: none;
}

.table thead th {
  font-size: 0.8rem;
  padding-top: 0 !important;
  padding-bottom: 0 !important;
  letter-spacing: 1px;
  text-transform: uppercase;
  border-bottom: 1px solid #e9ecef;
}

.table th {
  font-weight: 600;
}

.table td .progress {
  width: 120px;
  height: 3px;
  margin: 0;
}

.table td,
.table th {
  font-size: 1rem;
  white-space: nowrap;
}

.table.align-items-center td,
.table.align-items-center th {
  vertical-align: middle;
}


.table .thead-light th {
  color: #8898aa;
  background-color: #f6f9fc;
  text-align: center;
}

.table-flush td,
.table-flush th {
  border-right: 0;
  border-left: 0;
}

.table-flush tbody tr:first-child td,
.table-flush tbody tr:first-child th {
  border-top: 0;
}

.table-flush tbody tr:last-child td,
.table-flush tbody tr:last-child th {
  border-bottom: 0;
}

.card .table {
  margin-bottom: 0;

}

.card .table td,
.card .table th {
  padding-top: 12px;
  padding-bottom: 12px;
  padding-right: 1.5rem;
  padding-left: 1.5rem;

}

p {
  font-size: 2rem;
  font-weight: 300;
  line-height: 1.7;
}

@media (max-width: 768px) {
  .btn {
      margin-bottom: 10px;
  }
}

.date-tag {
  text-align: center !important;
}
</style>