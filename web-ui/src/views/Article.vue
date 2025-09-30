<template>
  <div>
    <div>
      <el-card class="margin-10">
        <el-input v-model="data.title" :prefix-icon="Search" placeholder="请输入查询内容"
                  style="width: 240px;"></el-input>
        <el-button @click="load" type="primary" style="margin-left: 15px;">查 询</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </el-card>

      <el-card class="margin-10">
        <el-button type="primary" @click="handlerAdd">新 增</el-button>
        <el-button type="warning" @click="delBatch">批量删除</el-button>
      </el-card>

      <el-card class="margin-10">
        <el-table :data="data.tableData" stripe @selection-change="handleSectionChange">
          <el-table-column type="selection"  width="55"/>
          <el-table-column label="账号" prop="title"/>
          <el-table-column label="封面" >
            <template #default="scope">
              <el-image v-if="scope.row.img" :src="scope.row.img"  :preview-src-list=[scope.row.img] preview-teleported alt="" style="height: 60px;width: 100px;" />
            </template>
          </el-table-column>
          <el-table-column label="描述" prop="description" show-overflow-tooltip/>
          <el-table-column label="操作">
            <template #default="scope">
              <el-button type="primary" @click="handleUpdate(scope.row)" :icon="Edit" circle></el-button>
              <el-button type="danger" @click="del(scope.row.id)" :icon="Delete" circle></el-button>
            </template>
          </el-table-column>
        </el-table>

        <div style="margin-top: 15px;">
          <el-pagination
              @current-change="load"
              @size-change="load"
              v-model:current-page="data.pageNum"
              v-model:page-size="data.pageSize"
              :page-sizes="[5,10,15,20]"
              background
              layout="total,sizes,prev,pager,next,jumper"
              :total="data.total"
          />
        </div>
      </el-card>
    </div>

    <el-dialog v-model="data.dialogFormVisible" title="文章信息" width="500">
      <el-form ref="formRef" :model="data.form" label-width="80px" style="padding-right: 50px; padding-top: 20px;">
        <el-form-item label="标题" prop="title">
          <el-input v-model="data.form.title" autocomplete="off" placeholder="请输入标题"/>
        </el-form-item>

        <el-form-item label="封面" >
          <el-upload
              action="http://localhost:8031/files/upload"
              list-type="picture"
              :on-success="handleAvatarSuccess">
            <el-button type="primary">上传封面</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="简介" prop="description">
          <el-input type="textarea" :rows="3" v-model="data.form.description" autocomplete="off"/>
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="save">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
import {Search, Delete, Edit} from "@element-plus/icons-vue";
import request from "@/utils/request.js"
import {ElMessage,ElMessageBox} from "element-plus"

const data = reactive({
  name: null,
  tableData: [],
  pageNum: 1,
  pageSize: 10,
  total: 0,
  dialogFormVisible: false,
  form: {},
  ids:[],
  rules:{
    username:[
      {required:true,message:"请输入账号",trigger:'blur'}
    ],
    name:[
      {required:true,message:"请输入名称",trigger:'blur'}
    ],

  }
})

const formRef = ref()

const load = () => {
  request.get('/article/getPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name
    }
  }).then(res => {
    data.tableData = res.data.list;
    data.total = res.data.total;
  })


}
load();

const reset = () => {
  data.name = null;
  load();
}

const handlerAdd = () => {
  data.dialogFormVisible = true
  data.form = {}

}

const add = () => {
  request.post('/article/add', data.form).then(res => {
    if (res.code == '200') {
      data.dialogFormVisible = false;
      ElMessage.success("操作成功");
      load();
    } else {
      ElMessage.error(res.message)
    }

  })
}

const update = () => {
  request.put('/article/update', data.form).then(res => {
    if (res.code == '200') {
      data.dialogFormVisible = false;
      ElMessage.success("操作成功");
      load();
    } else {
      ElMessage.error(res.message)
    }

  })
}

const save = () => {

  formRef.value.validate((valid)=>{
    if(valid){
      // 更新和保存
      data.form.id ? update() : add()
    }
  })
}

const del = (id) => {

  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {

    request.delete('/article/deleteById/' + id).then(res => {

      if (res.code == '200') {
        data.dialogFormVisible = false;
        ElMessage.success("操作成功");
        load();
      } else {
        ElMessage.error(res.message)
      }
    })

  }).catch()


}


const handleUpdate = (row) => {
  data.form = JSON.parse(JSON.stringify((row)));
  data.dialogFormVisible = true;
}

const handleSectionChange = (rows) =>{

    data.ids = rows.map(row => row.id);

  console.log(data.ids)
}

const delBatch= ()=> {

  if(data.ids.length === 0)
  {
      ElMessage.warning('请选择数据');
      return;
  }
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {

  request.delete('/article/deleteBatch',{data:data.ids}).then(res=>{

    if (res.code == '200') {
      data.dialogFormVisible = false;
      ElMessage.success("操作成功");
      load();
    } else {
      ElMessage.error(res.message)
    }


  });

  }).catch()

}

const handleAvatarSuccess = (res)=>{
  let fileName = res.data;
  console.log(fileName)
  data.form.img = res.data;
}

</script>

<style scoped>
.margin-10 {
  margin: 10px;
}

</style>