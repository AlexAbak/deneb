<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:lst="http://deneblingvo.org/xsd/transformator/list/1.0"
	xmlns:dst="http://deneblingvo.org/xsd/transformator/destination/1.0"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:lib="http://deneblingvo.org/xsd/deneb/library/1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	exclude-result-prefixes="#all">

	<xsl:output
		method="xml" 
		version="1.0"
		indent="yes"
		encoding="UTF-8"
		undeclare-prefixes="no" />

	<xsl:namespace-alias result-prefix="#default" stylesheet-prefix="#default"/>

	<xsl:template match="/">
		<xsl:element
			name="dst:destination"
			namespace="http://deneblingvo.org/xsd/transformator/destination/1.0">
			<xsl:element
				name="dst:file"
				namespace="http://deneblingvo.org/xsd/transformator/destination/1.0">
				<xsl:element
					name="lib:library"
					namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
					<xsl:apply-templates select="lst:list/xs:schema/xs:complexType" />
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="xs:complexType">
		<xsl:element
			name="lib:interface"
			namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
			<xsl:attribute name="name">
				<xsl:copy-of select="@name"/>
			</xsl:attribute>
			<xsl:apply-templates select="xs:attribute" />
		</xsl:element>
		<xsl:element
			name="lib:class"
			namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
			<xsl:attribute name="name">
				<xsl:copy-of select="@name"/>
			</xsl:attribute>
			<xsl:element
				name="lib:interface"
				namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
				<xsl:attribute name="name">
					<xsl:copy-of select="@name"/>
				</xsl:attribute>
			</xsl:element>
			<xsl:apply-templates select="xs:attribute">
				<xsl:with-param name="isWrite">true</xsl:with-param>
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>

	<xsl:template match="xs:attribute[@type='xs:string']">
		<xsl:param name="isWrite">false</xsl:param>
		<xsl:element
			name="lib:property"
			namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
			<xsl:attribute name="name">
				<xsl:copy-of select="@name"/>
			</xsl:attribute>
			<xsl:attribute name="type">string</xsl:attribute>
			<xsl:attribute name="isRead">true</xsl:attribute>
			<xsl:attribute name="isWrite"><xsl:value-of select="$isWrite"/></xsl:attribute>
		</xsl:element>
	</xsl:template>

	<xsl:template match="xs:attribute[@type='xs:int']">
		<xsl:param name="isWrite">false</xsl:param> 
		<xsl:element
			name="lib:property"
			namespace="http://deneblingvo.org/xsd/deneb/library/1.0">
			<xsl:attribute name="name">
				<xsl:copy-of select="@name"/>
			</xsl:attribute>
			<xsl:attribute name="type">integer</xsl:attribute>
			<xsl:attribute name="isRead">true</xsl:attribute>
			<xsl:attribute name="isWrite"><xsl:value-of select="$isWrite"/></xsl:attribute>
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>