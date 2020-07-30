package io.kotless.terraform.provider.aws.resource.route53

import io.kotless.hcl.HCLEntity
import io.kotless.terraform.TFFile
import io.kotless.terraform.TFResource
import io.kotless.utils.withIndent

/**
 * Terraform aws_route53_record resource.
 *
 * @see <a href="https://www.terraform.io/docs/providers/aws/r/route53_record.html">aws_route53_record</a>
 */
class Route53Record(id: String) : TFResource(id, "aws_route53_record") {
    var zone_id by text()
    var name by text()
    var type by text()
    var records by textArray()

    class Alias : HCLEntity.Inner("alias") {
        var name by text()
        var zone_id by text()
        var evaluate_target_health by bool()
    }

    fun alias(configure: Alias.() -> Unit) {
        inner(Alias().apply(configure))
    }
}

fun route53_record(id: String, configure: Route53Record.() -> Unit) = Route53Record(id).apply(configure)

fun TFFile.route53_record(id: String, configure: Route53Record.() -> Unit) {
    add(Route53Record(id).apply(configure))
}
