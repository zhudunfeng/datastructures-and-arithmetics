package com.adun;

import java.util.HashSet;
import java.util.Set;

/**
 * 快慢指针找出数组重复数
 *
 * @author Zhu Dunfeng
 * @date 2022/2/23 16:09
 */
public class DuplicateNum {

    public static void main(String[] args) {
//        int[] nums={5,4,3,2,1,6,7,2,2};
        int[] nums={5,4,1,2,1};
//        int i = duplicateNum1(nums);
//        int i = duplicateNum2(nums);
        int i = duplicateNum3(nums);
        System.out.println(i);
        System.out.println(5>>1);
    }


    public static int  duplicateNum1(int[] nums){
        int slow=0,fast=0;
        slow=nums[slow];
        fast=nums[nums[fast]];

        while (slow != fast){
            slow=nums[slow];
            fast=nums[nums[fast]];
        }

        //快指针回到起点
        fast=0;
        while (slow != fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return fast;
    }

    public static int  duplicateNum2(int[] nums){
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if(seen.contains(num)){
                return num;
            }
            seen.add(num);
        }
        return -1;
    }

    public static int  duplicateNum3(int[] nums){
        int n=nums.length;
        int left=1,right=n-1,ans=-1;
        while (left<=right){
            int mid=(left+right)>>1;
            int cnt=0;
            for (int i = 0; i < n; i++) {
                if(nums[i]<= mid){
                    cnt++;
                }
            }
            if(cnt<=mid){
                left=mid+1;
            }else {
                right=mid-1;
                ans=mid;
            }
        }
        return ans;
    }



}
