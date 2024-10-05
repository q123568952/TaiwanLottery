<template>
  <div class="mt-4">
    <el-input
      v-model="input3"
      style="max-width: 600px "
      placeholder="請輸入號碼，EX: 01,02,03"
      class="input-with-select"
    >
      <template #prepend>
        <el-select v-model="select" placeholder="分析方法" style="width: 115px">
          <el-option label="分析方法一" value="1" />
          <el-option label="分析方法二" value="2" />
          <el-option label="分析方法三" value="3" />
        </el-select>
      </template>
      <template #append>
        <el-button :icon="Search" />
      </template>
    </el-input>
  </div>
  
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="lotterytime" label="開獎時間"  />
    <el-table-column prop="lotterynumbers" label="開獎號碼"  />
  </el-table>
</template>
<script setup>
import { Search } from '@element-plus/icons-vue';
</script>
<script>

   export default {    
    data() {
      return {
        pagination: {//分頁
        currentPage: 0,
        pageSize: 10,
        total: 100,
        queryString: null,
      },    
      tableData: [],     
      select : "",
      input3: ""
      }  
    },
    //VUE初始化執行
    created() {
      this.findPage();
    },
    methods: {    
      //查询
      findPage() {      
        this.axios.get(`http://localhost:8080/findAll/${this.pagination.currentPage}/${this.pagination.pageSize}`).then((res)=>{                                       
                        this.tableData = res.data;                
            })
      }
    }
  }
</script>