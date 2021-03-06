	package com.example.abhinav.cricker;

	/**
	 * Created by Abhinav on 11-02-2018.
	 */


			import android.app.AlertDialog;
			import android.content.DialogInterface;
			import android.content.Intent;
			import android.renderscript.Double2;
			import android.support.v7.app.AppCompatActivity;
			import android.os.Bundle;
			import android.util.Log;
			import android.view.Gravity;
			import android.view.View;
			import android.widget.Button;
			import android.widget.EditText;
			import android.widget.LinearLayout;
			import android.widget.ListView;
			import android.widget.RadioButton;
			import android.widget.TableLayout;
			import android.widget.TableRow;
			import android.widget.TextView;
			import android.widget.LinearLayout.LayoutParams;
			import android.widget.Toast;

			import java.util.ArrayList;

	public class NewMatchActivity extends AppCompatActivity {


		private int score=0;
		private int wickets=0;
		private int overs=0;
		private int balls=0;
		private String runRate="";
		private int p1Runs=0;
		private int p1Balls=0;
		private int p1Fours=0;
		private int p1Sixes=0;
		private int p2Fours=0;
		private int p2Sixes=0;
		private int p2Runs=0;
		private int p2Balls=0;
		private String overDetails="";
		private boolean isCurrentStrikeP1=true;
		private boolean flag=false;

		private TextView scoreView;
		private TextView overs1View;
		private TextView overs2View;
		private TextView runRateView;

		private TextView player1RunsView;
		private TextView player1BallsView;
		private TextView player2RunsView;
		private TextView player2BallsView;

		private TextView thisOverKeyView;
		private TextView thisOverValueView;

		//TextView batsman1;
		//TextView batsman2;
		private RadioButton rad1;
		private RadioButton rad2;
		private TextView wicketsView;
		private ArrayList<BatsmenBean> batsList;


		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_new_match);
			scoreView = (TextView) findViewById(R.id.runs);
			overs1View = (TextView) findViewById(R.id.overs1);
			overs2View = (TextView) findViewById(R.id.overs2);
			runRateView = (TextView) findViewById(R.id.runrate);
			player1RunsView = (TextView) findViewById(R.id.player1RunsValue);
			player1BallsView = (TextView) findViewById(R.id.player1BallsValue);
			player2RunsView = (TextView) findViewById(R.id.player2RunsValue);
			player2BallsView = (TextView) findViewById(R.id.player2BallsValue);
			thisOverKeyView = (TextView) findViewById(R.id.thisOverKey);
			thisOverValueView = (TextView) findViewById(R.id.thisOverValue);
			rad1 = (RadioButton) findViewById(R.id.rad1);
			rad2 = (RadioButton) findViewById(R.id.rad2);
			wicketsView = (TextView) findViewById(R.id.wickets);
			batsList=new ArrayList<BatsmenBean>();


			/*ArrayList<BatsmenBean> wordsList = new ArrayList<BatsmenBean>();
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));
			wordsList.add(new BatsmenBean("Abhinav",100,50,4,10,200));

			Log.v("wordList size: ",wordsList.size()+"");

			BatsmenViewAdaptor adaptor=new BatsmenViewAdaptor(this,wordsList);
			ListView view =(ListView)findViewById(R.id.list_view);
			view.setAdapter(adaptor);*/
		}

		private void setCurrentBatsmen1(View view)
		{

			rad2.setChecked(false);
			rad1.setChecked(true);
		}
		private void setCurrentBatsmen2(View view)
		{
			rad1.setChecked(false);
			rad2.setChecked(true);

		}



		public void calculateScore(View view) {
			if(wickets>=10)
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("");
				builder.setMessage("Innings completed");
				builder.setCancelable(true);
				builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getApplicationContext(), "Innings completed", Toast.LENGTH_SHORT).show();
					}
				});
				builder.show();
				return;
			}


			Button btn=(Button)view;
			int run=0;
			try {
				String btnPressed = btn.getText().toString();
				if (btnPressed.equalsIgnoreCase("nb") || btnPressed.equalsIgnoreCase("wd"))
				{
					score = score + 1;
				}
				else if (btnPressed.equalsIgnoreCase("w"))
				{

					wickets = wickets + 1;
					balls = balls + 1;
					if(isCurrentStrikeP1)
					{
						batsList.add(new BatsmenBean("player"+(wickets),p1Runs,p1Balls,p1Fours,p1Sixes,calculateSR()));
						p1Runs=0;
						p1Balls=0;
						p1Fours=0;
						p1Sixes=0;

					}
					else
					{
						batsList.add(new BatsmenBean("player"+wickets,p2Runs,p2Balls,p2Fours,p2Sixes,calculateSR()));
						p2Runs = 0;
						p2Balls = 0;
						p2Fours=0;
						p2Sixes=0;
					}

					wicketsView.setText(wickets + "");

					getMatchStats();


				}
				else
				{
					run = Integer.parseInt(btnPressed);
					score = score + run;
					balls = balls + 1;



					if(isCurrentStrikeP1)
					{
						p1Runs=p1Runs+run;
						p1Balls=p1Balls+1;
						if(run==4)
						{
							p1Fours=p1Fours+1;

						}
						if(run==6)
						{
							p1Sixes=p1Sixes+1;
						}
					}
					else
					{
						p2Runs=p2Runs+run;
						p2Balls=p2Balls+1;
						if(run==4)
						{
							p2Fours=p2Fours+1;

						}
						if(run==6)
						{
							p2Sixes=p2Sixes+1;
						}
					}
					if(balls==6)
					{
						if(run%2==0)
							isCurrentStrikeP1 = isCurrentStrikeP1 ? false : true;
					}
					else
					{
						if (run == 1 || run == 3)
							isCurrentStrikeP1 = isCurrentStrikeP1 ? false : true;
					}



				}



				calculateRunRate(score);
				if(flag)
				{
					thisOverKeyView.setText("This Over: ");
					overDetails=btnPressed;
					thisOverValueView.setText(overDetails);
					flag=false;
				}
				else
				{
					thisOverKeyView.setText("This Over: ");

					if(overDetails.isEmpty())
						overDetails=btnPressed;
					else
						overDetails = overDetails + "-" + btnPressed;
					thisOverValueView.setText(overDetails);
				}

				if(isCurrentStrikeP1)
				{
					rad1.setChecked(true);
					rad2.setChecked(false);

				}
				else
				{
					rad1.setChecked(false);
					rad2.setChecked(true);
				}

				// to change over details
				if (balls == 6)
				{
					balls = 0;
					overs = overs + 1;
					thisOverKeyView.setText("Last Over: ");
					//overDetails=overDetails+"-"+btnPressed;
					// thisOverValueView.setText(overDetails);
					flag=true;

				}


				scoreView.setText(score + "");
				runRateView.setText(runRate);
				overs1View.setText(overs+"");
				overs2View.setText(balls+"");

				player1RunsView.setText(p1Runs+"");
				player1BallsView.setText(p1Balls+"");
				player2RunsView.setText(p2Runs+"");
				player2BallsView.setText(p2Balls+"");

				TextView p1runs=(TextView)findViewById(R.id.player1Runs);
				TextView p1balls=(TextView)findViewById(R.id.player1Balls);
				TextView p1fours=(TextView)findViewById(R.id.player1Fours);
				TextView p1sixes=(TextView)findViewById(R.id.player1Sixes);
				TextView p1Sr=(TextView)findViewById(R.id.player1StrikeRate);
				TextView p1name=(TextView)findViewById(R.id.player1name);

				p1name.setText("*Player"+(wickets+1));
				p1runs.setText(p1Runs+"");
				p1balls.setText(p1Balls+"");
				p1fours.setText(p1Fours+"");
				p1sixes.setText(p1Sixes+"");
				double sr1=((double)p1Runs/(double)p1Balls)*100;
				sr1=Double.isNaN(sr1)?0:sr1;
				p1Sr.setText(String.format("%.2f",sr1));

				TextView p2runs=(TextView)findViewById(R.id.player2Runs);
				TextView p2balls=(TextView)findViewById(R.id.player2Balls);
				TextView p2fours=(TextView)findViewById(R.id.player2Fours);
				TextView p2sixes=(TextView)findViewById(R.id.player2Sixes);
				TextView p2Sr=(TextView)findViewById(R.id.player2StrikeRate);
				TextView p2name=(TextView)findViewById(R.id.player2name);

				p2name.setText("*Player"+(wickets+2));

				p2runs.setText(p2Runs+"");
				p2balls.setText(p2Balls+"");
				p2fours.setText(p2Fours+"");
				p2sixes.setText(p2Sixes+"");

				p2Sr.setText(String.format("%.2f",calculateSR()));

			}
			catch (Exception e)
			{
				Log.getStackTraceString(e);
			}






		}
		public Double calculateSR()
		{
			double sr=((double)p2Runs/(double)p2Balls)*100;
			sr=Double.isNaN(sr)?0:sr;
			return sr;
		}
		public void getMatchStats()
		{



			TableLayout ll=(TableLayout)findViewById(R.id.table_layout);
			TableRow.LayoutParams  params1=new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1);
			TableRow.LayoutParams  params2=new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT,1);
				BatsmenBean bean=batsList.get(batsList.size()-1);

				params2.gravity=Gravity.RIGHT;
				TableRow row=new TableRow(this) ;
				TextView view_name=new TextView(this);
				view_name.setLayoutParams(params2);
				view_name.setText(bean.getName());
				row.addView(view_name);


				TextView view_runs=new TextView(this);
				view_runs.setLayoutParams(params1);
				view_runs.setText(bean.getRuns()+"");
				row.addView(view_runs);

				TextView view_balls=new TextView(this);
				view_balls.setLayoutParams(params1);
				view_balls.setText(bean.getBalls()+"");
				row.addView(view_balls);

				TextView view_4s=new TextView(this);
				view_4s.setLayoutParams(params1);
				view_4s.setText(bean.getFours()+"");
				row.addView(view_4s);

				TextView view_6s=new TextView(this);
				view_6s.setLayoutParams(params1);
				view_6s.setText(bean.getSixes()+"");
				row.addView(view_6s);

				TextView view_sr=new TextView(this);
				view_sr.setLayoutParams(params1);
				view_sr.setText(String.format("%.2f",bean.getStrikeRate()));
				row.addView(view_sr);
				int temp=wickets+1;
				ll.addView(row,temp);




		}


		public void calculateRunRate(int score)
		{
			if (overs == 0)
			{
				runRate=String.valueOf(score);

			}
			else if (overs > 0)
			{
				float k = (float) (overs * 6 + balls) / 6;
				float k1 = score / k;
				runRate=String.format("%.2f", k1);

			}
		}

		public void resetClick(View view)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Confirm reset");
			builder.setMessage("Yor are about to reset all the records.Do you want to proceed ?");
			builder.setCancelable(false);
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "You've choosen to delete all records", Toast.LENGTH_SHORT).show();
					Intent intent = getIntent();
					finish();
					startActivity(intent);
				}
			});
			builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {}});
			builder.show();




		}
		public void undo(View view)
		{
			Toast.makeText(getApplicationContext(), "Undo comming soon", Toast.LENGTH_SHORT).show();
		}



	}