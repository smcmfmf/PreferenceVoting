package kr.ac.kopo.preferencevoting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final int voteCount[] = new int[9]; // 배열 생성
        for (int i = 0; i < voteCount.length; i++)
        {
            voteCount[i] = 0; // 배열 초기화
        }
        ImageView imgv[] = new ImageView[9]; // 이미지 배열
        int imgvId[] = {R.id.imv0, R.id.imv1, R.id.imv2, R.id.imv3, R.id.imv4, R.id.imv5, R.id.imv6, R.id.imv7, R.id.imv8};
        final String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀",
                                "테라스의 두 자매", "피아노 레슨", "피아노 앞에 소녀들", "해변에서"}; // 이미지 제목

        for (int i = 0; i < imgv.length; i++)
        {
            final int index;
            index = i; // 없다면 반복문 마지막에 8번째 인덱스 값으로 고정됨

            imgv[index] = findViewById(imgvId[index]);
            imgv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index] + ": 총 " + voteCount[index] + "표", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnDone = findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("voteCount", voteCount);
                intent.putExtra("imgName", imgName);
                startActivity(intent);
            }
        });
    }
}