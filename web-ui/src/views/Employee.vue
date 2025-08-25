<template>
  <div>
    <div>
      <el-card class="margin-10">
        <el-input v-model="data.name" :prefix-icon="Search" placeholder="请输入查询内容"
                  style="width: 240px;"></el-input>
        <el-button @click="load" type="primary" style="margin-left: 15px;">查 询</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </el-card>

      <el-card class="margin-10">
        <el-button type="primary" @click="handlerAdd">新 增</el-button>
        <el-button type="warning" @click="delBatch">批量删除</el-button>
        <!--        <el-button type="info">导入</el-button>-->
        <!--        <el-button type="success">导出</el-button>-->
      </el-card>

      <el-card class="margin-10">
        <el-table :data="data.tableData" stripe @selection-change="handleSectionChange">
          <el-table-column type="selection"  width="55"/>
          <el-table-column label="名称" prop="name"/>
          <el-table-column label="性别" prop="sex"/>
          <el-table-column label="工号" prop="no"/>
          <el-table-column label="年龄" prop="age"/>
          <el-table-column label="个人介绍" prop="description" show-overflow-tooltip/>
          <el-table-column label="部门" prop="departmentName"/>
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

    <el-dialog v-model="data.dialogFormVisible" title="员工信息" width="500">
      <el-form :model="data.form" label-width="80px" style="padding-right: 50px; padding-top: 20px;">
        <el-form-item label="名称">
          <el-input v-model="data.form.name" autocomplete="off"/>
        </el-form-item>

        <el-form-item label="性别">
          <el-radio-group v-model="data.form.sex">
            <el-radio value="男" label="男"></el-radio>
            <el-radio value="女" label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工号">
          <el-input v-model="data.form.no" autocomplete="off"
                    placeholder="请输入工号"/>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number style="width:180px" :min="18" v-model="data.form.age" autocomplete="off"
                           placeholder="请输入年龄"></el-input-number>
        </el-form-item>
        <el-form-item label="个人介绍">
          <el-input :rows="3" type="textarea" v-model="data.form.description" autocomplete="off"
                    placeholder="请输入个人介绍"></el-input>
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
import {reactive} from "vue";
import {Search, Delete, Edit} from "@element-plus/icons-vue";
import request from "@/utils/request.js"
import {ElMessage,ElMessageBox} from "element-plus"

const data = reactive({
  name: null,
  tableData: [
    {id: 1, date: '2024-10-11', name: '亲哥哥哥', address: '安徽合肥'},
    {id: 2, date: '2024-10-12', name: '小鲁班', address: '上海浦东'},
    {id: 3, date: '2024-10-13', name: '小妲己', address: '北京大兴'}
  ],
  pageNum: 1,
  pageSize: 10,
  total: 0,
  dialogFormVisible: false,
  form: {},
  ids:[]
})

const load = () => {
  request.get('/employee/getPage', {
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
  request.post('/employee/add', data.form).then(res => {
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
  request.put('/employee/update', data.form).then(res => {
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

  // 更新和保存
  data.form.id ? update() : add()

}

const del = (id) => {

  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {

    request.delete('/employee/deleteById/' + id).then(res => {

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

  request.delete('/employee/deleteBatch',{data:data.ids}).then(res=>{

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


</script>

<style scoped>
.margin-10 {
  margin: 10px;
}

</style>