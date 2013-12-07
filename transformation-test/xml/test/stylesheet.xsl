<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:lst="http://deneblingvo.org/xsd/transformator/list/1.0"
	xmlns:dst="http://deneblingvo.org/xsd/transformator/destination/1.0"
	xmlns:exm="http://deneblingvo.org/xsd/transformator/Example/1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:output method="xml" version="1.0" indent="yes" encoding="UTF-8"/>

	<xsl:template
		match="/">
		<xsl:element
			name="dst:destination"
			namespace="http://deneblingvo.org/xsd/transformator/destination/1.0">
			<xsl:element
				name="dst:file"
				namespace="http://deneblingvo.org/xsd/transformator/destination/1.0">
				<xsl:element
					name="exm:database"
					namespace="http://deneblingvo.org/xsd/transformator/Example/1.0">
					<xsl:attribute 
						name="xsi:schemaLocation"
						namespace="http://www.w3.org/2001/XMLSchema-instance">http://deneblingvo.org/xsd/transformator/Example/1.0 Example.xsd</xsl:attribute>
					<xsl:namespace
						name="xsi">http://www.w3.org/2001/XMLSchema-instance</xsl:namespace>
					<xsl:for-each
						select="lst:list/exm:database">
						<xsl:copy-of
							select="exm:table" />
					</xsl:for-each>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

</xsl:stylesheet>