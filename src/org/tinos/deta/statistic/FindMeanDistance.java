package org.tinos.deta.statistic;
import java.util.Iterator;
import java.util.List;

import org.tinos.deta.basic.Distance;
import org.tinos.deta.demension.Position2D;
import org.tinos.deta.demension.Position3D;
public class FindMeanDistance{
	//求坐标团的主要有效距离成份集的平均压强算法
	//思想：罗瑶光 20191225
	//实现：罗瑶光
	public static double findMeanDistanceFromPositions2D(List<Position2D> position2Ds
			, double oberserverPCAScale, int sortRangeScale) {
		Iterator<Position2D> outPosition2DIterator= position2Ds.iterator();
		double outMean= 0;
		while(outPosition2DIterator.hasNext()) {
			Position2D outPosition2D= outPosition2DIterator.next();
			double[] distance= new double[position2Ds.size()];
			int i= 0;
			Iterator<Position2D> inPosition2DIterator= position2Ds.iterator();
			//取坐标点所有距离集合先
			while(inPosition2DIterator.hasNext()) {
				Position2D inPosition2D= inPosition2DIterator.next();
				distance[i++]= Distance.getDistance2D(outPosition2D, inPosition2D);
			}
			//距离非对称缺陷小高峰过滤极速快排 从小到大
			distance= new LYG4DWithDoubleQuickSort4D().sort(distance, sortRangeScale);
			//仅仅取精度内坐标点距离求平均值，0 为本身所以从 1 开始
			double inMean= 0;
			for(i= 1; i<= oberserverPCAScale; i++) {
				inMean+= distance[i];
			}
			inMean/= oberserverPCAScale;
			outMean+= inMean;
		}
		return outMean/ position2Ds.size();
	}
	
	public static double findMeanDistanceFromPositions3D(List<Position3D> position3Ds
			, double oberserverPCAScale, int sortRangeScale) {
		Iterator<Position3D> outPosition3DIterator= position3Ds.iterator();
		double outMean= 0;
		while(outPosition3DIterator.hasNext()) {
			Position3D outPosition3D= outPosition3DIterator.next();
			double[] distance= new double[position3Ds.size()];
			int i= 0;
			Iterator<Position3D> inPosition3DIterator= position3Ds.iterator();
			//取坐标点所有距离集合先
			while(inPosition3DIterator.hasNext()) {
				Position3D inPosition3D= inPosition3DIterator.next();
				distance[i++]= Distance.getDistance3D(outPosition3D, inPosition3D);
			}
			//距离非对称缺陷小高峰过滤极速快排 从小到大
			distance= new LYG4DWithDoubleQuickSort4D().sort(distance, sortRangeScale);
			//仅仅取精度内坐标点距离求平均值，0 为本身所以从 1 开始
			double inMean= 0;
			for(i= 1; i<= oberserverPCAScale; i++) {
				inMean+= distance[i];
			}
			inMean/= oberserverPCAScale;
			outMean+= inMean;
		}
		return outMean/ position3Ds.size();
	}
}