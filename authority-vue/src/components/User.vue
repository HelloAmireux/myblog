<template>
  <div>
    <div>
      <!--      搜索栏-->
      <el-input  style="width: 200px;margin-right: 20px" placeholder="请输入用户名" v-model="username" prefix-icon="el-icon-user"></el-input>
      <el-input  style="width: 200px" placeholder="请输入邮箱" v-model="email" prefix-icon="el-icon-message"></el-input>
      <el-button style="margin-left: 10px;" type="primary" @click="load" class="el-icon-search">搜索</el-button>
      <el-button style="margin-left: 10px;" type="warning" @click="reset" class="el-icon-refresh">重置</el-button>
    </div>

    <div style="margin-top:20px;margin-bottom: 20px;">
      <!--      新增。批量删除-->
      <el-button style="margin-right: 10px;" type="success"  class="el-icon-plus" @click="save">新增</el-button>
      <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="确定删除这些数据吗？"
          @confirm="deleteBatch"
          @cancel="cancel">
        <el-button slot="reference" type="danger" style="margin-left:5px;" class="el-icon-delete">批量删除</el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-style="getRowClass" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="phone" label="联系方式"></el-table-column>
      <el-table-column prop="roleId" label="角色"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="headerUrl" label="头像"></el-table-column>
      <el-table-column  label="操作" width="200">
        <template slot-scope="scope">
          <el-button  type="primary"  class="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除这些数据吗？"
              @confirm="handleDelete(scope.row.id)"
              @cancel="cancel">
            <el-button slot="reference" type="danger" style="margin-left:5px;" class="el-icon-delete">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div class="block" style="padding:10px 0;align-content: center;margin-left: 30%;margin-top:30px;">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="收货地址" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off" type="password" show-password></el-input>
        </el-form-item>
        <el-form-item label="昵称" :label-width="formLabelWidth">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAdd">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      pageSize:10,
      pageNum:1,
      username:'',
      email:'',
      tableData:[],
      dialogFormVisible:false,
      form:{},
      formLabelWidth: '80px',
      multipleSelection:[],
      total:0
    }
  },
  created() {
    this.load();
  },
  methods:{
    load(){
      this.request.get("/user/findPage",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:this.username,
          email:this.email
        }
      }).then(res => {
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    getRowClass({rowIndex,columnIndex}){
      if(rowIndex === 0){
        return 'background:#ccc'
      }
    },
    reset(){
      this.email = '';
      this.username = '';
      this.load();
    },
    save(){
      this.dialogFormVisible = true;
      this.form = {};
    },
    handleAdd(){
      this.request.post("/user/save",this.form).then(res => {
        if(res.code === '200'){
          if(this.form.id){
            this.$message.success('编辑成功');
          }else{
            this.$message.success('新增成功');
          }
          this.dialogFormVisible = false;
          this.load();
        }else{
          this.$message.error('操作失败，请联系管理员')
        }
      })
    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogFormVisible = true;
    },
    handleDelete(id){
      if(id){
        this.request.delete('/user/deleteById/' + id).then(res => {
          if(res.code === '200'){
            this.$message.success('删除数据成功');
            this.load();
          }else{
            this.$message.error('删除数据失败，请联系管理员')
          }
        })
      }else{
        this.$message.error('没有id信息，无法删除');
      }
    },
    cancel(){
      this.$message.success('取消操作成功');
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    deleteBatch(){
      //批量删除数据
      if(this.multipleSelection.length === 0){
        this.$message.warning("请先选择要删除的数据");
        return
      }
      const ids = this.multipleSelection.map(v => v.id);
      this.request.post('/user/deleteBatch',ids).then(res => {
        if(res.code === '200'){
          this.$message.success('批量删除成功');
          this.load();
        }else{
          this.$message.error('批量删除失败');
        }
      })
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.load();
    },
    handleCurrentChange(val) {
      this.pageNum = val;
      this.load();
    }
  }
}
</script>

<style scoped>

</style>
