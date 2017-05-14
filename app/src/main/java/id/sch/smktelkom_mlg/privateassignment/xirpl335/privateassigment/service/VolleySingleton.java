package id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.PlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl335.privateassigment.SoonFragment;

/**
 * Created by hyuam on 18/04/2017.
 */

public class VolleySingleton {
    private static volatile VolleySingleton mInstance;
    private static PlayingFragment mCtx;
    private static SoonFragment mCtx1;
    private RequestQueue mRequestQueue;

    private VolleySingleton(PlayingFragment context) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    private VolleySingleton(SoonFragment context1) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx1 = context1;
        mRequestQueue = getRequestQueue();
    }

    public static VolleySingleton getInstance(PlayingFragment context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public static VolleySingleton getInstance(SoonFragment context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getActivity());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
