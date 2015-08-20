package moziqi.te;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;

@SuppressWarnings("SimplifiableIfStatement")
public class HomeActivity extends TabActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabHost = this.getTabHost();
        TabHost.TabSpec homeSpec = tabHost.newTabSpec("home").setIndicator("home").setContent(new Intent(this, SettingsActivity.class));
        TabHost.TabSpec writeSpec = tabHost.newTabSpec("write").setIndicator("write").setContent(new Intent(this, SettingsActivity.class));
        TabHost.TabSpec msgSpec = tabHost.newTabSpec("msg").setIndicator("msg").setContent(new Intent(this, SettingsActivity.class));
        TabHost.TabSpec moreSpec = tabHost.newTabSpec("more").setIndicator("more").setContent(new Intent(this, SettingsActivity.class));
        tabHost.addTab(homeSpec);
        tabHost.addTab(writeSpec);
        tabHost.addTab(msgSpec);
        tabHost.addTab(moreSpec);
        RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.rg_tabs);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        tabHost.setCurrentTabByTag("home");
                        break;
                    case R.id.rb_write:
                        tabHost.setCurrentTabByTag("write");
                        break;
                    case R.id.rb_msg:
                        tabHost.setCurrentTabByTag("msg");
                        break;
                    case R.id.rb_more:
                        tabHost.setCurrentTabByTag("more");
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
