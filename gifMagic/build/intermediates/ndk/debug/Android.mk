LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := gifMagic
LOCAL_SRC_FILES := \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\Android.mk \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\Android.mk \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\ImageFile.cpp \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libavcodec.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libavcore.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libavdevice.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libavfilter.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libavformat.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libavutil.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\libswscale.a \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\VideoFile.cpp \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\VideoFileManager.cpp \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\video\VideoFrame.cpp \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\yuv420sp\Android.mk \
	E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni\yuv420sp\YUV420SP.c \

LOCAL_C_INCLUDES += E:\Projects\branches\Android\MyApplication\gifMagic\src\main\jni
LOCAL_C_INCLUDES += E:\Projects\branches\Android\MyApplication\gifMagic\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
