package com.codepath.apps.mysimpletweets;

// Taking the tweet objects and turning them into views to be displayed in the list

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        //super(context, android.R.layout.simple_list_item_1 ,tweets);
        super(context, 0 ,tweets); // with 0 the getView method is used.
    }

    // override and setup custom template
    // ViewHolder Pattern
    // View lookup cache
    private static class ViewHolder {
        ImageView ivProfileImage;
        TextView tvUserName;
        TextView tvBody;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//      WITHOUT VIEW HOLDER PATTERN
//      /*
//        1. get the tweet
//        2. find or inflate the template
//        3. find the subviews to fill with data in the template
//        4. populate data into the subviews
//        5. return the view to be inserted into the list
//         */
//        Tweet tweet = getItem(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
//        }
//
//        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
//        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
//        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
//
//        tvUserName.setText(tweet.getUser().getScreenName());
//        tvBody.setText(tweet.getBody());
//        ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for the recycled view . Can also use a default image
//        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);
//
//        return convertView;

        /*
        WITH VIEW HOLDER PATTERN
         */
        Tweet tweet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
            viewHolder.ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);

            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
            viewHolder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for the recycled view . Can also use a default image
        }

        viewHolder.ivProfileImage.setContentDescription(tweet.getUser().getScreenName());
        viewHolder.tvUserName.setText(tweet.getUser().getScreenName());
        viewHolder.tvBody.setText(tweet.getBody());
        //viewHolder.ivProfileImage.setImageResource(android.R.color.transparent); // clear out the old image for the recycled view . Can also use a default image
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(viewHolder.ivProfileImage);

        return convertView;

    }
}
