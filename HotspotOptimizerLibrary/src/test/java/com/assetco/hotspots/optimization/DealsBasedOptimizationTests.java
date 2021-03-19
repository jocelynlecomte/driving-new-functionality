package com.assetco.hotspots.optimization;

import com.assetco.search.results.UserSegment;
import org.junit.jupiter.api.Test;

import static com.assetco.search.results.HotspotKey.Deals;

public class DealsBasedOptimizationTests extends OptimizerTests {
    @Test
    public void partnerLevelDealsForNewsMediaSegment() {
        givenUserSegment(UserSegment.NewsMedia);
        // I did my best to make it clear what the role of each asset was
        var partnerLevel = givenAssetWith30DayProfitabilityAndDealEligibility(partnerVendor, "700", "1000", false);
        // It may not have been necessary to use this many assets, but I needed to verify that the rules
        // were changing for all classes of asset based on the highest vendor level
        var goldLevelIn = givenAssetWith30DayProfitabilityAndDealEligibility(goldVendor, "500", "1000", true);
        var goldLevelOut = givenAssetWith30DayProfitabilityAndDealEligibility(goldVendor, "500", "1000", false);
        var silverLevelIn = givenAssetWith30DayProfitabilityAndDealEligibility(silverVendor, "2500", "10000", true);
        var silverLevelOut = givenAssetWith30DayProfitabilityAndDealEligibility(silverVendor, "2500.1", "10000", true);
        var basicLevel = givenAssetWith30DayProfitabilityAndDealEligibility(basicVendor, "0", "100000", true);

        whenOptimize();

        thenHotspotHas(Deals, partnerLevel, goldLevelIn, silverLevelIn);
        thenHotspotDoesNotHave(Deals, goldLevelOut, silverLevelOut, basicLevel);
    }

    @Test
    public void goldLevelDealsForNewsMediaSegment() {
        givenUserSegment(UserSegment.NewsMedia);
        var goldLevelIn = givenAssetWith30DayProfitabilityAndDealEligibility(goldVendor, "700", "1000", true);
        var goldLevelOut = givenAssetWith30DayProfitabilityAndDealEligibility(goldVendor, "0", "999.99", true);
        var silverLevelIn = givenAssetWith30DayProfitabilityAndDealEligibility(silverVendor, "750", "1500", true);
        var silverLevelOut = givenAssetWith30DayProfitabilityAndDealEligibility(silverVendor, "750", "1500", false);
        var basicLevel = givenAssetWith30DayProfitabilityAndDealEligibility(basicVendor, "0", "100000", true);

        whenOptimize();

        thenHotspotHas(Deals, goldLevelIn, silverLevelIn);
        thenHotspotDoesNotHave(Deals, goldLevelOut, silverLevelOut, basicLevel);
    }

    @Test
    public void silverLevelDealsForNewsMediaSegment() {
        givenUserSegment(UserSegment.NewsMedia);
        var silverLevelIn = givenAssetWith30DayProfitabilityAndDealEligibility(silverVendor, "1050", "1500", true);
        var silverLevelOut = givenAssetWith30DayProfitabilityAndDealEligibility(silverVendor, "1050", "1500", false);
        var basicLevel = givenAssetWith30DayProfitabilityAndDealEligibility(basicVendor, "0", "100000", true);

        whenOptimize();

        thenHotspotHas(Deals, silverLevelIn);
        thenHotspotDoesNotHave(Deals, silverLevelOut, basicLevel);
    }
}
