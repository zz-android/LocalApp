package com.ruijie.localapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruijie.localapp.dialog.FloatingImageDisplayService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LocationActivity extends Activity {
    private Context mContext;
    private EditText speedET,tagET;
    private Button setBtn;
    private ListView locationBeanListView;
    private LocationBeanAdapter locationBeanAdapter;
    public static List<LocationBean> locationBeanList;
    public static Integer TAG = 0;
    public static int nowlocation = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mContext =this;

        speedET = findViewById(R.id.speedET);
        tagET = findViewById(R.id.tagET);
        tagET.setText(TAG+"");
        setBtn = findViewById(R.id.setBtn);
        speedET.setText(LocationBean.UPDATE_FREQ+"");
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int speed = Integer.parseInt(speedET.getText()+"");
                LocationBean.UPDATE_FREQ = speed;

                TAG = Integer.parseInt(tagET.getText()+"");

            }
        });



        locationBeanListView = (ListView) findViewById(R.id.locationBeanListView);

        locationBeanList = new ArrayList<LocationBean>();
        locationBeanAdapter = new LocationBeanAdapter(this,locationBeanList);
        locationBeanListView.setAdapter(locationBeanAdapter);
        locationBeanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, locationBeanList.get(position).getRemark(), Toast.LENGTH_SHORT).show();
                LocationBean bean = locationBeanList.get(position);
                double longitude = bean.getLongitudeReal();
                double altitude = bean.getAltitudeReal();
                LocationBean.staticLongitude = longitude;
                LocationBean.staticAltitude = altitude;
                nowlocation = position;

            }
        });

        locationBeanList.add(new LocationBean(119.1840,26.0531,"福ktv",1));
        locationBeanList.add(new LocationBean(119.1851,26.0545,"福公寓43",2));
        locationBeanList.add(new LocationBean(119.1857,26.0537,"福公寓41",2));
        locationBeanList.add(new LocationBean(119.1876,26.0539,"福公寓31",3));
        locationBeanList.add(new LocationBean(119.1884,26.0549,"福公寓25",1));
        locationBeanList.add(new LocationBean(119.1888,26.0552,"福公寓25东",1));
        locationBeanList.add(new LocationBean(119.1898,26.0547,"福操场西北",1));
        locationBeanList.add(new LocationBean(119.1901,26.0534,"福操场西南",3));
        locationBeanList.add(new LocationBean(119.1914,26.0532,"福操场东南",3));
        locationBeanList.add(new LocationBean(119.1915,26.0547,"福操场东北",2));
        locationBeanList.add(new LocationBean(119.1930,26.0562,"福行政南",3));
        locationBeanList.add(new LocationBean(119.1933,26.0582,"福行政北",2));
        locationBeanList.add(new LocationBean(119.1928,26.0591,"福行政北2",1));
        locationBeanList.add(new LocationBean(119.1923,26.0577,"福行政西北",1));
        locationBeanList.add(new LocationBean(119.1916,26.0569,"福行政西",3));
        locationBeanList.add(new LocationBean(119.1905,26.0569,"福行政西2",2));
        locationBeanList.add(new LocationBean(119.1884,26.0568,"福公寓24",2));
        locationBeanList.add(new LocationBean(119.1882,26.0576,"福公寓19",3));
        locationBeanList.add(new LocationBean(119.1881,26.0597,"福京元餐厅",2));
        locationBeanList.add(new LocationBean(119.1893,26.0593,"福田径",3));
        locationBeanList.add(new LocationBean(119.1898,26.0610,"福南楼",3));
        locationBeanList.add(new LocationBean(119.1913,26.0613,"福南楼东",3));
        locationBeanList.add(new LocationBean(119.1922,26.0643,"福东6教学",1));
        locationBeanList.add(new LocationBean(119.1913,26.0634,"福东1教学",1));
        locationBeanList.add(new LocationBean(119.1902,26.0624,"福西1教学",1));
        locationBeanList.add(new LocationBean(119.1886,26.0614,"福南楼西",1));
        locationBeanList.add(new LocationBean(119.1866,26.0609,"福公寓4",1));
        locationBeanList.add(new LocationBean(119.1843,26.0601,"福公寓57",3));
        locationBeanList.add(new LocationBean(119.1850,26.0619,"福阿庆",1));

        locationBeanList.add(new LocationBean(119.1850,26.0636,"江公寓23",2));
        //locationBeanList.add(new LocationBean(119.1829,26.0648,"江公寓11",1));
        locationBeanList.add(new LocationBean(119.1863,26.0635,"江嘿店",2));
        locationBeanList.add(new LocationBean(119.1879,26.0634,"福嘿店对面",3));
        locationBeanList.add(new LocationBean(119.1876,26.0646,"福嘿店北",1));
        locationBeanList.add(new LocationBean(119.1890,26.0648,"福建筑西2",1));
        locationBeanList.add(new LocationBean(119.1904,26.0648,"福建筑西1",1));
        locationBeanList.add(new LocationBean(119.1897,26.0664,"江教学4东南",1));
        locationBeanList.add(new LocationBean(119.1924,26.0668,"福北门",1));
        locationBeanList.add(new LocationBean(119.1905,26.0675,"江教学4东",1));
        locationBeanList.add(new LocationBean(119.1875,26.0671,"江教学4西南",1));
        locationBeanList.add(new LocationBean(119.1857,26.0690,"江公管",1));
        locationBeanList.add(new LocationBean(119.1869,26.0704,"江长安路",1));
        locationBeanList.add(new LocationBean(119.1862,26.0713,"医药学院",1));
        locationBeanList.add(new LocationBean(119.1875,26.0722,"江东南门南",1));
        locationBeanList.add(new LocationBean(119.1866,26.0728,"江东南门西",1));
        locationBeanList.add(new LocationBean(119.1852,26.0687,"江寿司东",3));
        locationBeanList.add(new LocationBean(119.1835,26.0680,"江寿司西",1));
        locationBeanList.add(new LocationBean(119.1827,26.0661,"江天天烤鱼1",3));
        locationBeanList.add(new LocationBean(119.1812,26.0665,"医天天烤鱼2",3));
        locationBeanList.add(new LocationBean(119.1792,26.0677,"医文印广告",3));
        locationBeanList.add(new LocationBean(119.1779,26.0696,"医文印北",3));
        locationBeanList.add(new LocationBean(119.1783,26.0700,"医文印北2",3));
        locationBeanList.add(new LocationBean(119.1805,26.0699,"医体育广场南",3));
        locationBeanList.add(new LocationBean(119.1815,26.0703,"医体育广场东",3));
        locationBeanList.add(new LocationBean(119.1827,26.0724,"医5教学号楼",1));
        locationBeanList.add(new LocationBean(119.1783,26.0729,"医5东门",1));
        locationBeanList.add(new LocationBean(119.1780,26.0738,"医5东门2",1));
        locationBeanList.add(new LocationBean(119.1793,26.0752,"医5操场",1));


//        locationBeanList.add(new LocationBean(119.1599,26.0840,"博士后B7"));
//        locationBeanList.add(new LocationBean(119.1577,26.0872,"*博士后B7北"));
//        locationBeanList.add(new LocationBean(119.1624,26.0941,"*博士后B7北2"));
//        locationBeanList.add(new LocationBean(119.1613,26.0941,"*博士后B7北3"));
//        locationBeanList.add(new LocationBean(119.1555,26.0987,"*博莱特"));
//        locationBeanList.add(new LocationBean(119.1527,26.0762,"职业1"));
//        locationBeanList.add(new LocationBean(119.1545,26.0762,"职业2"));
//        locationBeanList.add(new LocationBean(119.1552,26.0774,"职业3"));
//        locationBeanList.add(new LocationBean(119.1570,26.0774,"职业4"));
        locationBeanList.add(new LocationBean(119.1778,26.0793,"*时针园"));
        locationBeanList.add(new LocationBean(119.2399,26.0859,"农大1"));
        locationBeanList.add(new LocationBean(119.2941,26.0884,"东百",4));
        locationBeanList.add(new LocationBean(119.3374,26.0959,"耀隆"));
        locationBeanList.add(new LocationBean(119.3176,26.0568,"君临"));
//        locationBeanList.add(new LocationBean(119.3385,26.0563,"万达"));
//        locationBeanList.add(new LocationBean(119.3250,26.0583,"联邦"));
//        locationBeanList.add(new LocationBean(119.3568,26.0531,"*敖祥"));
        locationBeanList.add(new LocationBean(119.3522,26.0488,"*中捷"));
        locationBeanList.add(new LocationBean(119.3534,26.0486,"*富邦"));
//        locationBeanList.add(new LocationBean(119.3575,26.0465,"*神福"));
        locationBeanList.add(new LocationBean(119.3582,26.0487,"万科"));
        locationBeanList.add(new LocationBean(119.3511,26.0433,"*花海1"));
//        locationBeanList.add(new LocationBean(119.3484,26.0394,"*观音"));
//        locationBeanList.add(new LocationBean(119.3516,26.0355,"*闽江世纪"));
        locationBeanList.add(new LocationBean(119.3465,26.0298,"后板5"));
        locationBeanList.add(new LocationBean(119.3429,26.0283,"新农村"));
        locationBeanList.add(new LocationBean(119.3263,26.0356,"三叉街"));
        locationBeanList.add(new LocationBean(119.3136,26.0311,"正祥"));
        locationBeanList.add(new LocationBean(119.3310,26.0286,"林则徐"));
        locationBeanList.add(new LocationBean(119.3331,26.0275,"详泰"));
        locationBeanList.add(new LocationBean(119.3384,26.0226,"都会12"));
        locationBeanList.add(new LocationBean(119.3429,26.0221,"吉若"));
        locationBeanList.add(new LocationBean(119.3481,26.0216,"环保"));
        locationBeanList.add(new LocationBean(119.3448,26.0131,"*洋楼"));
        locationBeanList.add(new LocationBean(119.3546,25.9977,"*城门教堂"));
        locationBeanList.add(new LocationBean(119.3562,25.9802,"*书院"));



        locationBeanList.add(new LocationBean(119.3810,26.0174,"南生态",3));
        locationBeanList.add(new LocationBean(119.4183,25.9944,"慈航向下-----1",1));
//        locationBeanList.add(new LocationBean(119.4136,25.9909,"祁阳",1));
//        locationBeanList.add(new LocationBean(119.4140,25.9878,"门头",1));
//        locationBeanList.add(new LocationBean(119.4137,25.9864,"八江",1));
//        locationBeanList.add(new LocationBean(119.4158,25.9753,"宋城文化",1));
//        locationBeanList.add(new LocationBean(119.4170,25.9747,"宋城文化2",1));
        locationBeanList.add(new LocationBean(119.4182,25.9742,"宋城文化3",1));
        locationBeanList.add(new LocationBean(119.4186,25.9477,"*下洋"));
        locationBeanList.add(new LocationBean(119.4133,25.9460,"*下洋2"));
        locationBeanList.add(new LocationBean(119.4339,25.9534,"*华云"));
        locationBeanList.add(new LocationBean(119.4581,25.9523,"*大王"));
        locationBeanList.add(new LocationBean(119.4635,25.9514,"*新区"));



//        locationBeanList.add(new LocationBean(119.3429,26.02215,"吉若！！！！！！！！！！",3));
//        locationBeanList.add(new LocationBean(119.3515,26.0277,"*世纪城"));
//        locationBeanList.add(new LocationBean(119.3527,26.0264,"*世纪金源"));
//        locationBeanList.add(new LocationBean(119.3557,26.0214,"*水岸"));
//        locationBeanList.add(new LocationBean(119.3617,26.0179,"*林甫"));
//        locationBeanList.add(new LocationBean(119.3617,26.0206,"*潘敦新城5区"));
//        locationBeanList.add(new LocationBean(119.3605,26.0222,"*潘敦新城4区"));
//        locationBeanList.add(new LocationBean(119.3583,26.0244,"*外滩"));
//        locationBeanList.add(new LocationBean(119.3602,26.0301,"*会展"));
//        locationBeanList.add(new LocationBean(119.3536,26.0268,"*闽江克拉"));
//        locationBeanList.add(new LocationBean(119.3567,26.0310,"*会展"));
//        locationBeanList.add(new LocationBean(119.3647,26.0263,"*规划"));
//        locationBeanList.add(new LocationBean(119.3542,26.0408,"*蔚蓝"));
//        locationBeanList.add(new LocationBean(119.34389,26.04670,"*花海2",2));
        locationBeanList.add(new LocationBean(119.4132,25.9990,"-----突袭海峡文化"));
        locationBeanList.add(new LocationBean(119.4183,25.9944,"慈航"));
        locationBeanList.add(new LocationBean(119.4225,25.9861,"万科"));
        locationBeanList.add(new LocationBean(119.4182,25.9742,"宋城文化3"));
        locationBeanList.add(new LocationBean(119.4531,25.9929,"13-6尚层"));
        locationBeanList.add(new LocationBean(119.4713,26.0066,"贸易试验区"));
        locationBeanList.add(new LocationBean(119.5088,26.0617,"炮台"));
        locationBeanList.add(new LocationBean(119.4968,26.0697,"象山"));
        locationBeanList.add(new LocationBean(119.5044,26.0820,"张宗"));
        locationBeanList.add(new LocationBean(119.5034,26.0855,"龙华"));

        locationBeanList.add(new LocationBean(119.3857,26.0247,"东方名康俊"));
        locationBeanList.add(new LocationBean(119.3568,26.0531,"*敖祥"));
        locationBeanList.add(new LocationBean(119.3568,26.0548,"*敖祥佳"));
        locationBeanList.add(new LocationBean(119.3544,26.0548,"*水岸"));
        locationBeanList.add(new LocationBean(119.3557,26.0583,"*北绿洲"));
        locationBeanList.add(new LocationBean(119.3496,26.0563,"*鳌峰院"));
        locationBeanList.add(new LocationBean(119.3470,26.0582,"*阳光"));
        locationBeanList.add(new LocationBean(119.3457,26.0603,"*鳌峰小区"));
        locationBeanList.add(new LocationBean(119.3428,26.0611,"*亚峰"));
        locationBeanList.add(new LocationBean(119.3432,26.0573,"*福人"));
        locationBeanList.add(new LocationBean(119.3410,26.0553,"*海1"));
        locationBeanList.add(new LocationBean(119.3436,26.0522,"*海2"));
        locationBeanList.add(new LocationBean(119.3522,26.0488,"*中捷",2));
        locationBeanList.add(new LocationBean(119.3534,26.0486,"*富邦",2));
        locationBeanList.add(new LocationBean(119.3201,26.0903,"*世纪城"));


        locationBeanList.add(new LocationBean(119.4575,25.9858,"-----针对三木"));
        locationBeanList.add(new LocationBean(119.4518,25.9835,"罗星塔"));
        locationBeanList.add(new LocationBean(119.4526,25.9965,"君山花园"));
        locationBeanList.add(new LocationBean(119.4603,26.0024,"君竹"));
        locationBeanList.add(new LocationBean(119.4565,26.0104,"水岸"));
        locationBeanList.add(new LocationBean(119.4534,26.0115,"三木廊院"));
        locationBeanList.add(new LocationBean(119.4287,26.0148,"文化站"));
        locationBeanList.add(new LocationBean(119.4325,26.0125,"六江村"));
        locationBeanList.add(new LocationBean(119.4158,26.0172,"名城港湾c"));
        locationBeanList.add(new LocationBean(119.4097,26.0214,"名郡"));
        locationBeanList.add(new LocationBean(119.3956,26.0184,"滑板"));
        locationBeanList.add(new LocationBean(119.3977,26.0257,"禾郡"));
        locationBeanList.add(new LocationBean(119.3857,26.0247,"东方名康俊"));
        locationBeanList.add(new LocationBean(119.3886,26.0253,"东方名华俊"));

        locationBeanList.add(new LocationBean(119.4183,25.9944,"慈航向右------2",1));
        locationBeanList.add(new LocationBean(119.4281,26.0097,"国脉时代"));
        locationBeanList.add(new LocationBean(119.4357,26.0113,"开发区"));
        locationBeanList.add(new LocationBean(119.4410,26.0073,"阳光学院公寓"));
        locationBeanList.add(new LocationBean(119.4517,26.0022,"天马山"));
        locationBeanList.add(new LocationBean(119.4492,25.9905,"13-1鸿瑞"));
        locationBeanList.add(new LocationBean(119.4530,25.9901,"13-2海西"));
        locationBeanList.add(new LocationBean(119.4549,25.9903,"13-3罗星"));
        locationBeanList.add(new LocationBean(119.4562,25.9894,"13-4英华"));
        locationBeanList.add(new LocationBean(119.4571,25.9921,"13-5阳光"));
        locationBeanList.add(new LocationBean(119.4531,25.9929,"13-6尚层"));
        locationBeanList.add(new LocationBean(119.4713,26.0066,"贸易试验区"));
        locationBeanList.add(new LocationBean(119.4989,26.0530,"兴慈"));
        locationBeanList.add(new LocationBean(119.5013,26.0470,"正心"));
        locationBeanList.add(new LocationBean(119.5068,26.0646,"碧桂园"));
        locationBeanList.add(new LocationBean(119.5056,26.0675,"宏江"));
        locationBeanList.add(new LocationBean(119.5088,26.0617,"炮台"));

        locationBeanList.add(new LocationBean(119.4972,26.0729,"西亭"));
        locationBeanList.add(new LocationBean(119.5044,26.0820,"张宗"));
        locationBeanList.add(new LocationBean(119.5034,26.0855,"龙华"));
        locationBeanList.add(new LocationBean(119.5069,26.0847,"盛美"));
        locationBeanList.add(new LocationBean(119.5121,26.0800,"海西幸福"));
        locationBeanList.add(new LocationBean(119.5138,26.0807,"长顺"));
        locationBeanList.add(new LocationBean(119.5150,26.0936,"龙津"));
        locationBeanList.add(new LocationBean(119.5183,26.0919,"陇西"));
        locationBeanList.add(new LocationBean(119.5206,26.0934,"华光"));
        locationBeanList.add(new LocationBean(119.5263,26.0999,"英辉"));

        locationBeanList.add(new LocationBean(119.5425,26.1168,"下平"));
        locationBeanList.add(new LocationBean(119.5385,26.1145,"感天"));
        locationBeanList.add(new LocationBean(119.5394,26.1121,"感天大厦"));
        locationBeanList.add(new LocationBean(119.5369,26.1091,"公园"));
        locationBeanList.add(new LocationBean(119.5325,26.1126,"江山首府"));

        locationBeanList.add(new LocationBean(119.5332,26.1281,"青云"));
        locationBeanList.add(new LocationBean(119.5420,26.1427,"青芝"));


//        locationBeanList.add(new LocationBean(119.4968,26.0697,"象山"));
//        locationBeanList.add(new LocationBean(119.4973,26.0690,"圣母"));
//        locationBeanList.add(new LocationBean(119.5069,26.0662,"基督"));
//        locationBeanList.add(new LocationBean(119.5087,26.0659,"长岛湾"));
//        locationBeanList.add(new LocationBean(119.5324,26.0612,"猴予1"));
//        locationBeanList.add(new LocationBean(119.5368,26.0599,"猴予2"));
//        locationBeanList.add(new LocationBean(119.5363,26.0579,"猴予3"));

        locationBeanList.add(new LocationBean(119.4183,25.9944,"慈航向左-----3",1));
        locationBeanList.add(new LocationBean(119.4199,26.0175,"名城"));
        locationBeanList.add(new LocationBean(119.4214,26.0277,"快安"));
        locationBeanList.add(new LocationBean(119.4175,26.0207,"2儒江"));

        locationBeanList.add(new LocationBean(119.4144,26.0194,"2江滨锦城"));
        locationBeanList.add(new LocationBean(119.4175,26.0227,"2华映"));
        locationBeanList.add(new LocationBean(119.4163,26.0239,"2华映家园"));
        locationBeanList.add(new LocationBean(119.4141,26.0220,"2江滨锦城3期"));
        locationBeanList.add(new LocationBean(119.4097,26.0214,"-2名郡"));
        locationBeanList.add(new LocationBean(119.4080,26.0172,"双协"));
        locationBeanList.add(new LocationBean(119.4025,26.0177,"母"));
        locationBeanList.add(new LocationBean(119.3978,26.0193,"蓝波湾"));
        locationBeanList.add(new LocationBean(119.3956,26.0184,"-滑板"));
        locationBeanList.add(new LocationBean(119.3977,26.0257,"-禾郡"));
        locationBeanList.add(new LocationBean(119.4051,26.0341,"-龙门"));
        locationBeanList.add(new LocationBean(119.4018,26.0289,"-工业区"));
        locationBeanList.add(new LocationBean(119.3949,26.0286,"魁协"));
        locationBeanList.add(new LocationBean(119.3904,26.0266,"凯隆"));
        locationBeanList.add(new LocationBean(119.3897,26.0295,"华"));
        locationBeanList.add(new LocationBean(119.3857,26.0247,"-东方名康俊"));
        locationBeanList.add(new LocationBean(119.3887,26.0431,"鼓山1"));
        locationBeanList.add(new LocationBean(119.3856,26.0499,"鼓山2"));
        locationBeanList.add(new LocationBean(119.3864,26.0543,"鼓山3"));
        locationBeanList.add(new LocationBean(119.3882,26.0535,"鼓山4"));
        locationBeanList.add(new LocationBean(119.3933,26.0565,"鼓山5"));
        locationBeanList.add(new LocationBean(119.3919,26.0573,"鼓山6"));
        locationBeanList.add(new LocationBean(119.3868,26.0588,"鼓山7"));
        locationBeanList.add(new LocationBean(119.3840,26.0561,"鼓山8"));
        locationBeanList.add(new LocationBean(119.3798,26.0563,"半山亭"));
        locationBeanList.add(new LocationBean(119.3727,26.0533,"半山亭左"));
        locationBeanList.add(new LocationBean(119.3727,26.0563,"半山亭左2"));
        locationBeanList.add(new LocationBean(119.3699,26.0614,"飞卢"));
        locationBeanList.add(new LocationBean(119.3689,26.0622,"禹州"));
        locationBeanList.add(new LocationBean(119.3642,26.0611,"硫酸"));
        locationBeanList.add(new LocationBean(119.3633,26.0631,"远东1"));
        locationBeanList.add(new LocationBean(119.3620,26.0629,"远东2"));
        locationBeanList.add(new LocationBean(119.3593,26.0615,"三远"));
        locationBeanList.add(new LocationBean(119.3682,26.0778,"樟林"));
        locationBeanList.add(new LocationBean(119.3658,26.0851,"王爷庙"));

        locationBeanList.add(new LocationBean(119.3429,26.02215,"吉若",3));
        locationBeanList.add(new LocationBean(119.3448,26.0131,"*洋楼"));










        locationBeanList.add(new LocationBean(119.4370,25.9984,"阳光瑞景"));
        locationBeanList.add(new LocationBean(119.4407,25.9949,"马尾基督"));
        locationBeanList.add(new LocationBean(119.4422,25.9929,"马尾福顺"));
        locationBeanList.add(new LocationBean(119.4434,25.9924,"马尾造船"));
        locationBeanList.add(new LocationBean(119.4511,25.9956,"罗建"));
        locationBeanList.add(new LocationBean(119.4511,25.9976,"聚富"));
        locationBeanList.add(new LocationBean(119.4529,26.0043,"军岐"));



        locationBeanList.add(new LocationBean(119.3066,26.0774,"正大",4));
//        locationBeanList.add(new LocationBean(119.3085,26.0832,"大华",4));
        locationBeanList.add(new LocationBean(119.3109,26.0793,"一品",4));
//        locationBeanList.add(new LocationBean(119.3102,26.0776,"闽都",4));
//        locationBeanList.add(new LocationBean(119.3113,26.0747,"海潮",4));
        locationBeanList.add(new LocationBean(119.3080,26.0723,"重庆",4));
        locationBeanList.add(new LocationBean(119.3019,26.0714,"世贸",4));
        locationBeanList.add(new LocationBean(119.3031,26.0771,"剧院",4));
        locationBeanList.add(new LocationBean(119.3027,26.0796,"万年青",4));
        locationBeanList.add(new LocationBean(119.3023,26.0803,"于山",4));
        locationBeanList.add(new LocationBean(119.3000,26.0832,"榕城酒店",4));

        locationBeanList.add(new LocationBean(119.3023,26.0803,"于山！！！！！",4));
        locationBeanList.add(new LocationBean(119.3031,26.0771,"剧院！！！！！",4));
        locationBeanList.add(new LocationBean(119.29410,26.08842,"东百",4));




//
//        locationBeanList.add(new LocationBean(119.3000,26.0832,"榕城酒店",4));
//        locationBeanList.add(new LocationBean(119.29410,26.08842,"东百",4));
//        locationBeanList.add(new LocationBean(119.2910,26.0885,"冰心",4));
//        locationBeanList.add(new LocationBean(119.2962,26.0808,"冠亚",4));





        locationBeanList.add(new LocationBean(117.020438,25.05950,"龙岩"));
        locationBeanList.add(new LocationBean(117.64486,26.27405,"三明"));
        locationBeanList.add(new LocationBean(118.18219,26.64074,"南平"));
        locationBeanList.add(new LocationBean(117.72303,24.46459,"漳州"));
        locationBeanList.add(new LocationBean(119.58540,26.67444,"宁德"));
        locationBeanList.add(new LocationBean(119.12509,25.32325,"莆田"));

//        locationBeanList.add(new LocationBean(119.30550,26.03733,"*师大",1));
//        locationBeanList.add(new LocationBean(119.3011,26.03227,"*师大3",3));
//
//        locationBeanList.add(new LocationBean(119.2821,26.0892,"湖头"));
//        locationBeanList.add(new LocationBean(119.2959,26.0894,"coco1"));
//        locationBeanList.add(new LocationBean(119.2912,26.0879,"coco2"));
//        locationBeanList.add(new LocationBean(119.2996,26.0904,"井大"));
//
//        locationBeanList.add(new LocationBean(119.26404,26.04180,"仓山万达"));
//        locationBeanList.add(new LocationBean(119.3068,26.1126,"电建二公司"));
//        locationBeanList.add(new LocationBean(119.3196,26.1434,"五四北泰和"));
//
//        locationBeanList.add(new LocationBean(120.14879,30.25769,"杭州"));
//        locationBeanList.add(new LocationBean(116.34967,39.95718,"北京"));
//        locationBeanList.add(new LocationBean(106.48772,29.53881,"重庆"));
//        locationBeanList.add(new LocationBean(126.69108,45.76292,"哈尔滨"));
//        locationBeanList.add(new LocationBean(108.97154,34.29408,"西安"));

//        locationBeanList.add(new LocationBean(119.2895,26.1312,"*健康家园"));
//        locationBeanList.add(new LocationBean(119.2883,26.1345,"*康桥2"));
//        locationBeanList.add(new LocationBean(119.2873,26.1366,"*康桥"));
//        locationBeanList.add(new LocationBean(119.2889,26.1399,"*琴声商务"));
//        locationBeanList.add(new LocationBean(119.2957,26.1536,"*森林"));
//        locationBeanList.add(new LocationBean(119.2941,26.1533,"*森林2"));
//        locationBeanList.add(new LocationBean(119.2931,26.1531,"*森林3"));


        locationBeanAdapter.notifyDataSetChanged();
        if (!FloatingImageDisplayService.isStarted) {
            startService(new Intent(mContext, FloatingImageDisplayService.class));
        }

    }

    public void startFloatingDisplayClick(View view) {

//        if (!Settings.canDrawOverlays(this)) {
//            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
//            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 1);
//        } else {
//            startService(new Intent(MainActivity.this, FloatingImageDisplayService.class));
//        }
        if (FloatingImageDisplayService.isStarted) {
            return;
        }
//        if(LocationBean.staticLongitude == 0 && LocationBean.staticAltitude == 0){
//            LocationBean.staticLongitude = 119.29542;
//            LocationBean.staticAltitude = 26.08603;
//        }


        startService(new Intent(mContext, FloatingImageDisplayService.class));
    }
}