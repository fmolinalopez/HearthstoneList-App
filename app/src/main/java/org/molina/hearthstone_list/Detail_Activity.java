package org.molina.hearthstone_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.molina.hearthstone_list.model.Card;

public class Detail_Activity extends AppCompatActivity {

    ImageView mCardImage;
    TextView mCardNameTextView;
    TextView mCardCostTextView;
    TextView mCardTypeTextView;
    TextView mCardAttackTextView;
    TextView mCardHealthTextView;
    TextView mCardEffectTextView;
    Card mCard = new Card();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        mCardImage = findViewById(R.id.card_image_view);
        mCardNameTextView = findViewById(R.id.card_name);
        mCardCostTextView = findViewById(R.id.card_cost);
        mCardTypeTextView = findViewById(R.id.card_type);
        mCardAttackTextView = findViewById(R.id.card_attack);
        mCardHealthTextView = findViewById(R.id.card_health);
        mCardEffectTextView = findViewById(R.id.card_effect);

        Intent intent = Detail_Activity.this.getIntent();

        if (intent.hasExtra("card")){
            mCard = (Card) intent.getSerializableExtra("card");
        }

        Picasso.with(this).load(mCard.getImage()).into(mCardImage);
        mCardNameTextView.setText(mCard.getName());
        mCardCostTextView.setText(mCard.getCost() + "");
        mCardTypeTextView.setText(mCard.getType());
        mCardAttackTextView.setText(mCard.getAttack() + "");
        mCardHealthTextView.setText(mCard.getHealth() + "");
        mCardEffectTextView.setText(mCard.getEffect());
    }
}
