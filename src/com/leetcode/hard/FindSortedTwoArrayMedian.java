package com.leetcode.hard;

/**
 * 寻找两个正序数组的中位数
 *
 * https://blog.csdn.net/chen_xinjia/article/details/69258706
 *
 * @author 10005655 tianluye
 */
public class FindSortedTwoArrayMedian {

    /*
    给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

    请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

    你可以假设 nums1 和 nums2 不会同时为空。
     */

    public static void main(String[] args) {
        FindSortedTwoArrayMedian median = new FindSortedTwoArrayMedian();
        int[] nums1 = {2, 3};
        int[] nums2 = {2, 4, 7};
        double result = median.findMedianSortedArr(nums1, nums2);
        System.out.println(result);
    }

    public double findMedianSortedArr(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 == 0 && N2 == 0) {
            return 0.0;
        }
        if (N1 > N2) {
            // 确保后面执行的时候, N1是短的数组长度
            return findMedianSortedArr(nums2, nums1);
        }
        if (N1 == 0) {
            // 存在一个数组为空, 兼容 nums2是奇数位还是偶数位
            return (nums2[(N2 - 1) / 2] + nums2[(N2 / 2)]) / 2.0;
        }
        // 兼容切点在最左最右的场景
        int MIN_VALUE = 0x8000000;
        int MAX_VALUE = 0x7fffffff;
        int size = N1 + N2;
        // 定义 nums1 切点的可选区间
        int cutL = 0, cutR = N1;
        // 定义切点的位置
        int cut1 = N1 / 2, cut2 = 0;
        while (cut1 <= N1) {
            // 获取本次切割点，取可切割区间的中间数
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = size / 2 - cut1;
            // 计算切点的边界值
            double L1 = 0 == cut1 ? MIN_VALUE : nums1[cut1 - 1];
            double R1 = N1 == cut1 ? MAX_VALUE : nums1[cut1];
            double L2 = 0 == cut2 ? MIN_VALUE : nums2[cut2 - 1];
            double R2 = N2 == cut2 ? MAX_VALUE : nums2[cut2];
            if (L1 > R2) {
                // 若切点1的左值大于切点2的右值，那么可切割区间需要左移
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                // 若切点2的左值大于切点1的右值，那么可切割区间需要右移
                cutL = cut1 + 1;
            } else {
                if (size % 2 == 0) {
                    L1 = (L1 > L2 ? L1 : L2);
                    R1 = (R1 < R2 ? R1 : R2);
                    return (L1 + R1) / 2;
                }
                else {
                    R1 = (R1 < R2 ? R1 : R2);
                    return R1;
                }
            }
        }
        return 0.0;
    }

    /**
     * 最直白的想法就是：对有序数组进行归并排序，整合为一个数组，然后取其中位数。
     * 但是这样的做法的时间复杂度是 O(m + n)。
     *
     * @param nums1 数组 1
     * @param nums2 数组 2
     * @return 2个数组中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 全部为空，则返回 0.0
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        }
        // 存在某个数组为空
        if (nums1.length == 0 || nums2.length == 0) {
            int[] nums = nums1.length == 0 ? nums2 : nums1;
            return findOneArrayMedian(nums);
        }
        // 合并 2个有序数组
        int totalNum = nums1.length + nums2.length;
        int one = 0, two = 0, index = 0;
        int[] nums = new int[totalNum];
        while (index < totalNum) {
            if (one == nums1.length) {
                nums[index] = nums2[two];
                index++;
                two++;
                continue;
            }
            if (two == nums2.length) {
                nums[index] = nums1[one];
                index++;
                one++;
                continue;
            }
            if (nums1[one] >= nums2[two]) {
                nums[index] = nums2[two];
                two++;
            } else {
                nums[index] = nums1[one];
                one++;
            }
            index++;
        }
        return findOneArrayMedian(nums);
    }

    /**
     * 从数组里获取中位数
     *
     * @param nums 有序数组
     * @return 中位数
     */
    private double findOneArrayMedian(int[] nums) {
        // 升序不为空的 nums数组
        int index = nums.length / 2;
        return nums.length % 2 == 0 ? (nums[index - 1] + nums[index]) / 2.0 : nums[index];
    }

}
