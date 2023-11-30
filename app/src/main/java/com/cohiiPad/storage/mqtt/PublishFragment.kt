package com.cohiiPad.storage.mqtt

import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.cohiiPad.storage.MqttActivity
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttToken
import com.cohiiPad.storage.R

class PublishFragment : BaseFragment() {
    private var mTopic: EditText? = null
    private var mPayload: EditText? = null
    private var mQosRadioGroup: RadioGroup? = null
    private var mRetainedRadioGroup: RadioGroup? = null
    var mAdapter: PublishRecyclerViewAdapter? = null
    var mPublishList: ArrayList<Publish> = ArrayList()
    override val layoutResId: Int
        get() = R.layout.fragment_publish_list

    override fun setUpView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.publication_list)
        mAdapter = PublishRecyclerViewAdapter(mPublishList)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                fragmentActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        mTopic = view.findViewById(R.id.topic)
        mPayload = view.findViewById(R.id.payload)
        mQosRadioGroup = view.findViewById(R.id.qos)
        mRetainedRadioGroup = view.findViewById(R.id.retained)
        val pubBtn = view.findViewById<Button>(R.id.publish)
        pubBtn.setOnClickListener {
            val publish = publish
            (fragmentActivity as MqttActivity).publish(publish, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    fragmentActivity?.runOnUiThread {
                        mPublishList.add(0, publish)
                        mAdapter!!.notifyItemInserted(0)
                    }
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private val publish: Publish
        get() {
            val topic = mTopic!!.text.toString()
            val message = mPayload!!.text.toString()
            var qos = 0
            when (mQosRadioGroup!!.checkedRadioButtonId) {
                R.id.qos0 -> qos = 0
                R.id.qos1 -> qos = 1
                R.id.qos2 -> qos = 2
            }
            var retained = false
            when (mRetainedRadioGroup!!.checkedRadioButtonId) {
                R.id.retained_true -> retained = true
                R.id.retained_false -> retained = false
            }
            return Publish(topic, message, qos, retained)
        }

    companion object {
        fun newInstance(): PublishFragment {
            return PublishFragment()
        }
    }
}