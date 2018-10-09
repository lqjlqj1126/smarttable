package com.fst.smarttable;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private SmartTable table;
    Column<String> name;
    Column<String> subtp;
    Column<String> desp;
    Column<String> jcyj;
    Column<String> level;
    Column<Boolean> operation;
    List<String> name_selected = new ArrayList<String>();
    private View btnPrev,btnNext,tvPages;
    int start=0,limit=20,currentpage=1,rows=0,pages=0;
    String prjid="C7177E25E2D04a188A0859A099A512BD";
    ArrayList<Scrthrt> datas = new ArrayList();
    int size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = (SmartTable<Scrthrt>)findViewById(R.id.table);
        size = DensityUtils.dp2px(this,30);
        btnPrev=findViewById(R.id.btnPrev);
        btnNext=findViewById(R.id.btnNext);
        tvPages=findViewById(R.id.tvPages);
        btnPrev.setOnClickListener(prevPage());
        btnNext.setOnClickListener(nextPage());
        fetchDataThread();
    }

    private void showName(int s,boolean b){
        Toast.makeText(MainActivity.this,"内容"+s,Toast.LENGTH_SHORT).show();
    }
    private View.OnClickListener prevPage(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(currentpage>1)
                {
                    start=currentpage*limit;
                    currentpage--;
                    fetchDataThread();
                }

            }
        };
    }
    private View.OnClickListener nextPage(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(currentpage<pages)
                {
                    start=currentpage*limit;
                    currentpage++;
                    fetchDataThread();
                }

            }
        };
    }
    private void fetchDataThread()
    {
        Runnable run = new Runnable()
        {
            @Override
            public void run()
            {
                int rows=0;
                if(start==0)
                {
                    rows=DBUtil.QueryScrthrtCountByPrjid(prjid);
                }
                datas=DBUtil.QueryScrthrtByPrjid(prjid,start,limit);
                Message msg = new Message();
                msg.what=1001;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("entities", datas);
                bundle.putInt("rows", rows);
                msg.setData(bundle);
                mHandler.sendMessage(msg);

            }
        };
        new Thread(run).start();
    }
    Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what)
            {
                case 1001:
                    Bundle bundle=msg.getData();

                    ArrayList<Scrthrt> list =bundle.getParcelableArrayList("entities");

                    if(start==0)
                    {
                        rows=bundle.getInt("rows");
                        pages=(int)Math.ceil(rows/limit);
                    }
                    ((TextView) tvPages).setText(currentpage+"/"+pages+"(共有"+rows+"行)");
                    name = new Column<>("名字", "name");
                    name.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
                        @Override
                        public void onClick(Column<String> column, String value, String bool, int position) {
                            Toast.makeText(MainActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                            if(operation.getDatas().get(position)){
                                showName(position, false);
                                operation.getDatas().set(position,false);
                            }else{
                                showName(position, true);
                                operation.getDatas().set(position,true);
                            }
                            table.refreshDrawableState();           //不要忘记刷新表格，否则选中效果会延时一步
                            table.invalidate();
                        }
                    });
                    subtp = new Column<>("类别", "subtp");
                    name.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
                        @Override
                        public void onClick(Column<String> column, String value, String bool, int position) {
                            Toast.makeText(MainActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                            if(operation.getDatas().get(position)){
                                showName(position, false);
                                operation.getDatas().set(position,false);
                            }else{
                                showName(position, true);
                                operation.getDatas().set(position,true);
                            }
                            table.refreshDrawableState();           //不要忘记刷新表格，否则选中效果会延时一步
                            table.invalidate();
                        }
                    });
                    desp = new Column<>("描述", "desp");
                    name.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
                        @Override
                        public void onClick(Column<String> column, String value, String bool, int position) {
                            Toast.makeText(MainActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                            if(operation.getDatas().get(position)){
                                showName(position, false);
                                operation.getDatas().set(position,false);
                            }else{
                                showName(position, true);
                                operation.getDatas().set(position,true);
                            }
                            table.refreshDrawableState();           //不要忘记刷新表格，否则选中效果会延时一步
                            table.invalidate();
                        }
                    });
                    jcyj = new Column<>("检测依据", "jcyj");
                    name.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
                        @Override
                        public void onClick(Column<String> column, String value, String bool, int position) {
                            Toast.makeText(MainActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                            if(operation.getDatas().get(position)){
                                showName(position, false);
                                operation.getDatas().set(position,false);
                            }else{
                                showName(position, true);
                                operation.getDatas().set(position,true);
                            }
                            table.refreshDrawableState();           //不要忘记刷新表格，否则选中效果会延时一步
                            table.invalidate();
                        }
                    });
                    level = new Column<>("隐患级别", "level");
                    name.setOnColumnItemClickListener(new OnColumnItemClickListener<String>() {
                        @Override
                        public void onClick(Column<String> column, String value, String bool, int position) {
                            Toast.makeText(MainActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                            if(operation.getDatas().get(position)){
                                showName(position, false);
                                operation.getDatas().set(position,false);
                            }else{
                                showName(position, true);
                                operation.getDatas().set(position,true);
                            }
                            table.refreshDrawableState();           //不要忘记刷新表格，否则选中效果会延时一步
                            table.invalidate();
                        }
                    });


                    operation = new Column<>("选择", "checked", new ImageResDrawFormat<Boolean>(size,size) {    //设置"操作"这一列以图标显示 true、false 的状态
                        @Override
                        protected Context getContext() {
                            return MainActivity.this;
                        }
                        @Override
                        protected int getResourceID(Boolean isCheck, String value, int position) {
                            if(isCheck){
                                return R.drawable.checked;      //将图标提前放入 app/res/mipmap 目录下
                            }
                            return R.drawable.uncheck;
                        }
                    });
                    operation.setComputeWidth(40);
                    operation.setOnColumnItemClickListener(new OnColumnItemClickListener<Boolean>() {
                        @Override
                        public void onClick(Column<Boolean> column, String value, Boolean bool, int position) {
//                Toast.makeText(CodeListActivity.this,"点击了"+value,Toast.LENGTH_SHORT).show();
                            if(operation.getDatas().get(position)){
                                showName(position, false);
                                operation.getDatas().set(position,false);
                            }else{
                                showName(position, true);
                                operation.getDatas().set(position,true);
                            }
                            table.refreshDrawableState();
                            table.invalidate();
                        }
                    });

                    final TableData<Scrthrt> tableData = new TableData<Scrthrt>("测试标题",list, operation,
                            name, subtp,desp,jcyj,level );
                    table.getConfig().setShowTableTitle(false);

                    table.setTableData(tableData);
//        table.getConfig().setContentStyle(new FontStyle(50, Color.BLUE));
                    //table.getConfig().setMinTableWidth(1024);       //设置表格最小宽度
                    FontStyle style = new FontStyle();
                    style.setTextSize(30);
                    table.getConfig().setContentStyle(style);       //设置表格主题字体样式
                    table.getConfig().setColumnTitleStyle(style);   //设置表格标题字体样式
                    table.getConfig().setContentCellBackgroundFormat(new BaseCellBackgroundFormat<CellInfo>() {     //设置隔行变色
                        @Override
                        public int getBackGroundColor(CellInfo cellInfo) {
                            if(cellInfo.row%2 ==1) {
                                return ContextCompat.getColor(MainActivity.this, R.color.gray);      //需要在 app/res/values 中添加 <color name="tableBackground">#d4d4d4</color>
                            }else{
                                return TableConfig.INVALID_COLOR;
                            }
                        }
                    });
                    table.setZoom(true);

                    break;
                default:
                    break;
            }
        };
    };
}