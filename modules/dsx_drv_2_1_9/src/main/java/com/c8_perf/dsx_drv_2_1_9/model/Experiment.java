package com.c8_perf.dsx_drv_2_1_9.model;

import java.util.Date;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(
		keyspace = "c8_perf", name = "experiment",
		readConsistency = "LOCAL_ONE",
		writeConsistency = "LOCAL_ONE",
		caseSensitiveKeyspace = false,
		caseSensitiveTable = false
	)
public class Experiment {

	@PartitionKey
	//// @ApiModelProperty(value = "unique experiment ID", dataType = "UUID",  required = true)
    private UUID id;
    public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getSamplingPercent() {
		return samplingPercent;
	}
	public void setSamplingPercent(Double samplingPercent) {
		this.samplingPercent = samplingPercent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getRuleJson() {
		return ruleJson;
	}
	public void setRuleJson(String ruleJson) {
		this.ruleJson = ruleJson;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getModificationTime() {
		return modificationTime;
	}
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getIsPersonalizationEnabled() {
		return isPersonalizationEnabled;
	}
	public void setIsPersonalizationEnabled(Boolean isPersonalizationEnabled) {
		this.isPersonalizationEnabled = isPersonalizationEnabled;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelVersion() {
		return modelVersion;
	}
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}
	public Boolean getIsRapidExperiment() {
		return isRapidExperiment;
	}
	public void setIsRapidExperiment(Boolean isRapidExperiment) {
		this.isRapidExperiment = isRapidExperiment;
	}
	public Integer getUserCap() {
		return userCap;
	}
	public void setUserCap(Integer userCap) {
		this.userCap = userCap;
	}
	public String getCreatorID() {
		return creatorID;
	}
	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}
	// @ApiModelProperty(value = "experiment label; unique within the application", dataType = "String",  required = true)
    private String label;
    // @ApiModelProperty(value = "name of the application; e.g. \"QBO\"", dataType = "String",  required = true)
    private String applicationName;
    // @ApiModelProperty(value = "earliest time the experiment allows bucket assignments", required = true)
    private Date startTime;
    // @ApiModelProperty(value = "latest time the experiment allows bucket assignments", required = true)
    private Date endTime;
    // @ApiModelProperty(value = "probability of an eligible user being assigned into the experiment; " +
    //        "in range: (0, 1]",
    //        required = true)
    private Double samplingPercent;
    // @ApiModelProperty(value = "description of the experiment", required = false)
    private String description;
    // @ApiModelProperty(value = "defines a user segment, i.e., if the rule validates to true, user is part of the segment", required = false)
    private String rule;
    // @ApiModelProperty(value = "defines a user segment in json, i.e., if the rule validates to true, user is part of the segment", required = false)
    private String ruleJson;
    // @ApiModelProperty(value = "time experiment was created", required = true)
    private Date creationTime;
    // @ApiModelProperty(value = "last time experiment was modified", required = true)
    private Date modificationTime;
    // @ApiModelProperty(value = "state of the experiment", required = true)
    private String state;
    // @ApiModelProperty(value = "is personalization enabled for this experiment", required = false)
    private Boolean isPersonalizationEnabled;
    // @ApiModelProperty(value = "model name", required = false)
    private String modelName;
    // @ApiModelProperty(value = "model version no.", required = false)
    private String modelVersion;
    // @ApiModelProperty(value = "is this a rapid experiment", required = false)
    private Boolean isRapidExperiment;
    // @ApiModelProperty(value = "maximum number of users to allow before pausing the experiment", required = false)
    private Integer userCap;
    // @ApiModelProperty(value = "creator of the experiment", required = false)
    private String creatorID;

    
}
