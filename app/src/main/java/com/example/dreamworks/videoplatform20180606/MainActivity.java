package com.example.dreamworks.videoplatform20180606;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dreamworks.videoplatform20180606.beans.VPBean;
import com.example.dreamworks.videoplatform20180606.treeImpl.Dept;
import com.example.dreamworks.videoplatform20180606.treeImpl.Node;
import com.example.dreamworks.videoplatform20180606.treeImpl.NodeHelper;
import com.example.dreamworks.videoplatform20180606.treeImpl.NodeTreeAdapter;
import com.example.dreamworks.videoplatform20180606.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Tag";
    private boolean mPermissions;
    private ListView mListView;
    private NodeTreeAdapter mAdapter;
    private LinkedList<Node> mLinkedList = new LinkedList<>();
    private List<VPBean.DataBean> mList = new ArrayList<>();
    private LinearLayout rel;
    private VideoView mVideoView;
    private ImageView play, pause, reStart;
    private TextView tv_title_main;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 检查存储权限是否需要动态获取
        boolean b = PermissionUtil.needCheckPermission();
        if (b) {
            mPermissions = PermissionUtil.getExternalStoragePermissions(this, 0);
        }
        initView();
    }

    private void initView() {
        mListView = findViewById(R.id.m_listView);
        mVideoView = findViewById(R.id.videoView);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        loading = findViewById(R.id.load);
        reStart = findViewById(R.id.reStart);
        tv_title_main = findViewById(R.id.tv_title_main);
        rel = findViewById(R.id.rel);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        reStart.setOnClickListener(this);
        mAdapter = new NodeTreeAdapter(this, mListView, mLinkedList);
        mListView.setAdapter(mAdapter);
        rel.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getData();
        mVideoView.setMediaController(new MediaController(this));
        mAdapter.setOnThisClickListener(new NodeTreeAdapter.OnThisClickListener() {
            @Override
            public void OnItemClickListener(String position) {
                // 不能传位置position，只能传递数值
                if (position.contains(".mp4")) {
                    getLoading();
                    tv_title_main.setText(position);
                    mVideoView.stopPlayback();
//                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                    String url = "http://49.4.11.118:8080/VideoPlateform/videos/".concat(position);
//                    intent.putExtra("video", url);
//                    intent.putExtra("title", position);
//                    startActivity(intent);
                    mVideoView.setVideoURI(Uri.parse(url));
                    mVideoView.start();
                }
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                getPause();
                pause.setVisibility(View.GONE);
                mVideoView.start();
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                getRestart();
            }
        });
    }

    public void getData() {
        Call<VPBean> mainList = VPApplication.dataImpl.getMainList();
        final List<Node> dataBean = new ArrayList<>();
        mainList.enqueue(new Callback<VPBean>() {
            @Override
            public void onResponse(Call<VPBean> call, Response<VPBean> response) {
                VPBean body = response.body();
                if (body != null) {
                    int state = body.getState();
                    if (state == 200) {
                        List<VPBean.DataBean> data = body.getData();
                        int size = data.size();
                        if (size > 0) {
                            for (int i = 0; i < size; i++) {
                                mList.add(body.getData().get(i));
                                dataBean.add(new Dept(data.get(i).getID(), data.get(i).getParentID(), data.get(i).getNodeName()));
                            }
                            mLinkedList.addAll(NodeHelper.sortNodes(dataBean));
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "信息请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<VPBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void getPlay() {
        play.setVisibility(View.VISIBLE);
        pause.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        reStart.setVisibility(View.GONE);
    }

    private void getPause() {
        play.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        reStart.setVisibility(View.GONE);
    }

    private void getLoading() {
        play.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        reStart.setVisibility(View.GONE);
    }

    private void getRestart() {
        play.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        reStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                Toast.makeText(this, "请先选择播放内容", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pause:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                } else {
                    mVideoView.start();
                }
                break;
            case R.id.reStart:
                reStart.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                mVideoView.resume();
                break;
        }
    }
}
