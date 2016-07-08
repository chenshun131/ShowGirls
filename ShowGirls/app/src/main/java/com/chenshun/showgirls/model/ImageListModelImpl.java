package com.chenshun.showgirls.model;

import com.chenshun.showgirls.Constant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * User: chenshun <p />
 * Time: 2016/7/8 09:22  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class ImageListModelImpl implements ImageListModel
{
    @Override
    public void GetImageList(final String type, final int page, final GetImageListenter listener)
    {
        Observable<List<ImageListDomain>> observable = Observable.create(new Observable.OnSubscribe<List<ImageListDomain>>()
        {
            @Override
            public void call(Subscriber<? super List<ImageListDomain>> subscriber)
            {
                List<ImageListDomain> imageListDomainList = new ArrayList();
                try
                {
                    Document document = Jsoup.connect(Constant.BASE_URL + type + page).get();
                    Element imageListelement = document.getElementById("blog-grid");
                    Elements imageListElements = imageListelement.getElementsByAttributeValueContaining("class", "col-lg-4 col-md-4 three-columns post-box");
                    for (Element imageListElement : imageListElements)
                    {
                        Element link = imageListElement.select("a[href]").first();
                        Element image = imageListElement.select("img").first();
                        String linkUrl = link.attr("abs:href");
                        String imageUrl = image.attr("abs:src");
                        String imageTitle = image.attr("alt").trim();
                        imageListDomainList.add(new ImageListDomain(linkUrl, imageUrl, imageTitle));
                    }
                    subscriber.onNext(imageListDomainList);
                }
                catch (IOException e)
                {
                    subscriber.onError(e);
                }
            }
        });
        Subscriber<List<ImageListDomain>> subscriber = new Subscriber<List<ImageListDomain>>()
        {
            @Override
            public void onCompleted()
            {
            }

            @Override
            public void onError(Throwable e)
            {
                listener.OnError((Exception) e);
            }

            @Override
            public void onNext(List<ImageListDomain> imageListDomains)
            {
                listener.onSuccess(imageListDomains);
            }
        };
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    public interface GetImageListenter
    {
        void onSuccess(List<ImageListDomain> imageList);

        void OnError(Exception e);
    }
}
