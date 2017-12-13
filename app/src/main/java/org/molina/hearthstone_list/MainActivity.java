package org.molina.hearthstone_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.molina.hearthstone_list.model.Card;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView mListViewCards;
    ArrayList<Card> mCardList = new ArrayList<>();
    Card[] mCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewCards = findViewById(R.id.list_view_cards);

        final ArrayAdapter<Card> adapter = new ArrayAdapter<Card>(
                this,
                R.layout.list_item_card,
                R.id.text_view_cards,
                mCardList
        );

        mListViewCards.setAdapter(adapter);

        mListViewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detailIntent = new Intent(MainActivity.this, Detail_Activity.class);
                detailIntent.putExtra("card", mCardList.get(i));
                startActivity(detailIntent);
            }
        });

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://pure-dawn-45882.herokuapp.com/api/cards", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("AsyncTask", "Failure");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("AsyncTask", "Peticion OK: "+ responseString);
                Gson gson = new GsonBuilder().create();
                mCards = gson.fromJson(responseString, Card[].class);
                adapter.clear();
                for (Card card : mCards){
                    adapter.add(card);
                }
            }
        });
    }

}
