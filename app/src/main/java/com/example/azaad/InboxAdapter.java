package com.example.azaad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.azaad.InboxDataClass2.obj;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder>

    {
        private LayoutInflater inflater;
        private ArrayList<InboxDataClass> imageModelArrayList;
        private Context ctx;
        private Activity activity;
        private int trustflag;

        public InboxAdapter(Context ctx, Activity activity, ArrayList<InboxDataClass> imageModelArrayList){
            inflater = LayoutInflater.from(ctx);
            this.ctx = ctx;
            this.activity = activity;
            this.imageModelArrayList = imageModelArrayList;
            this.trustflag = trustflag;


        }
        UserCompleteDataArray userCompleteDataArray;
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            int itemheight = parent.getHeight() / 2;

            View view = inflater.inflate(R.layout.chatitems, parent, false);

//            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//            layoutParams.height = itemheight;
//            view.setLayoutParams(layoutParams);

            MyViewHolder holder = new MyViewHolder(view);


            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            //Glide.with(ctx).load("http://aapfilms.tv//videos//thumbnail//cFBEpGmx947_chotabheem.jpg").placeholder(R.drawable.aapfilms_black).into(holder.iv);

            //  Glide.with(ctx).load(R.mipmap.home).placeholder(R.drawable.aapfilms_black).into(holder.iv);

//            Toast.makeText(ctx, " "+imageModelArrayList.get(position).getImg().toString(), Toast.LENGTH_SHORT).show();
//            //  holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
//            holder.title.setText(imageModelArrayList.get(position).getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent intent = new Intent(holder.itemView.getContext(),ChatActivity.class);
                   // Toast.makeText(ctx, ""+position, Toast.LENGTH_SHORT).show();
//                    SessionManager sessionManager = new SessionManager(holder.itemView.getContext());
//                    sessionManager.setkeyBigStreaminfo(imageModelArrayList.get(position));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                    holder.itemView.getContext().startActivity(new Intent(intent));
                    int p=position;
                   Intent intent = new Intent(holder.itemView.getContext(), ChatActivity.class);
                   intent.removeExtra("position");
                   intent.putExtra("position",p);
                   holder.itemView.getContext().startActivity(intent);

                    obj.setUser_name(imageModelArrayList.get(position).getUser_name());
                    obj.setReceiverid(imageModelArrayList.get(position).getReceiverid());
                    obj.setUserimage(imageModelArrayList.get(position).getUserimage());
                    Toast.makeText(ctx, ""+obj.getUserimage(), Toast.LENGTH_SHORT).show();



//
//                    intent.putExtra("receiverid",imageModelArrayList.get(position).getShowuser()); // this one
                    //(String userid,String userimage,String username,String bigliveid)

//                   UserInfoClassData userInfoClassData = new UserInfoClassData();
//                    userInfoClassData.setUser_image(imageModelArrayList.get(position).getUserimage()+"");
//                    userInfoClassData.setFname(imageModelArrayList.get(position).getUser_name()+"");
//                    userInfoClassData.setLname(" ");
//                    userInfoClassData.setUserid(imageModelArrayList.get(position).getShowuser());
//                    userInfoClassData.setCity(imageModelArrayList.get(position).get()+"");
//                    userInfoClassData.setUserid(userid);
//
//                    new SessionManager(ctx).setkeyBigSearchUserinfo(userInfoClassData);

                  ;

/*
                    MyJobs.jobsInfoClassData = new JobsInfoClassData();
                    .jobsInfoClassData.setUserid(imageModelArrayList.get(position).getShowuser());
                    MyJobs.jobsInfoClassData.setUser_name(imageModelArrayList.get(position).getUser_name());
                    MyJobs.jobsInfoClassData.setUser_image(imageModelArrayList.get(position).getUserimage());


                    holder.itemView.getContext().startActivity(intent);*/



                }
            });

            new ImageLoadTask(holder,position,activity).execute(imageModelArrayList.get(position));
        }
        @Override
        public int getItemCount() {
            return imageModelArrayList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView text_message_name,text_message_time,text_message_body,text_level;
            CircleImageView image_message_profile;

            public MyViewHolder(View itemView) {
                super(itemView);

                text_message_name = (TextView) itemView.findViewById(R.id.text_message_name);
                text_message_time = (TextView) itemView.findViewById(R.id.text_message_time);
                text_message_body = (TextView) itemView.findViewById(R.id.text_message_body);

                image_message_profile = (CircleImageView) itemView.findViewById(R.id.image_message_profile);
            }

        }

        private static class ImageLoadTask extends AsyncTask<InboxDataClass, Void, String> {

            MyViewHolder viewHolder;
            int position;
            SessionManager sessionManager;
            Activity thecontext;

            private ImageLoadTask(MyViewHolder viewHolder, int position, Activity thecontext) {
                this.viewHolder = viewHolder;
                this.position = position;
                // CustomProgress.show(thecontext);
                this.thecontext = thecontext;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected String doInBackground(final InboxDataClass... teamData) {
                //  CustomProgress.hide();

                try {
                    thecontext.runOnUiThread(new Runnable() {
                        public void run() {


                            //  TextView text_message_name,text_message_time,text_message_body;
                            //    ImageView image_message_profile;

                            Glide.with(thecontext).load("http://circularbyte.com/Azaad_Matrimonial/"+teamData[0].getUserimage()).apply(new RequestOptions().placeholder(R.drawable.background).error(R.drawable.background)).into(viewHolder.image_message_profile);

                            viewHolder.text_message_body.setText(teamData[0].getLastmessage());
                            viewHolder.text_message_time.setText(teamData[0].getCreated_date());
                            viewHolder.text_message_name.setText(teamData[0].getUser_name());




//                            viewHolder.iframe.setText(teamData[0].getIframe());
                            //   viewHolder.imageView.setImageResource(R.mipmap.home);
//                            Toast.makeText(thecontext, "Executed Background", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                catch (Exception e)
                {
                    thecontext.runOnUiThread(new Runnable() {

                        public void run() {
                            //   Toast.makeText(thecontext, "Exception Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                return "done";
            }

            @Override
            protected void onPostExecute(String string) {
                // myMethod(rowview);

                super.onPostExecute(string);
            }


        }




}
